package com.acme.edu.server;


import com.acme.edu.printer.FilePrinter;
import com.acme.edu.printer.PrinterException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Server that logs input messages and saves them with the FilePrinter into file.
 */
public class Server {
    FilePrinter filePrinter;
    ServerSocket serverSocket;

    Thread serverLoop;

    public static void main(String[] args) throws PrinterException, IOException, ServerException, InterruptedException {
        Server server = new Server(new FilePrinter("test_server_log", StandardCharsets.UTF_8), new ServerSocket(8887));
        server.startLogServer();
        Thread.sleep(15_000);
        server.stopServer();
    }

    public Server(FilePrinter filePrinter, ServerSocket serverSocket) {
        this.filePrinter = filePrinter;
        this.serverSocket = serverSocket;
    }

    /**
     * Main loop. Will be ended after 15 seconds without input requests.
     * If exception arises in message processing procedure, then
     * exception will be serialized and sent back to the client.
     *
     * @throws ServerException
     */
    public void startLogServer() throws ServerException, InterruptedException {
        serverLoop = new Thread(() -> {
            try {
                serverSocket.setSoTimeout(5_000);
                ExecutorService fileLoggers = Executors.newFixedThreadPool(5);
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Socket socket = serverSocket.accept();
                        fileLoggers.submit(new SocketHandler(socket, filePrinter));
                    } catch (SocketTimeoutException e) {
                    }
                }
                fileLoggers.shutdown();
                fileLoggers.awaitTermination(5_000, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
//                throw new ServerException(e);
            } finally {
                try {
                    serverSocket.close();
                    filePrinter.close();
                } catch (Exception e) {
//                    throw new ServerException(e);
                }
            }
        });
        serverLoop.start();
    }

    public void stopServer() {
        serverLoop.interrupt();
    }

    private class SocketHandler implements Callable {
        Socket socket;
        FilePrinter filePrinter;

        public SocketHandler(Socket socket, FilePrinter filePrinter) {
            this.socket = socket;
            this.filePrinter = filePrinter;
        }

        @Override
        public Object call() throws IOException {
            handleSocket(filePrinter, socket);
            return null;
        }

        private void handleSocket(FilePrinter filePrinter, Socket socket) throws IOException {
            try (InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                String message = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    message += line;
                }
                processMessage(filePrinter, message);
            } catch (Exception e) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(new ServerException(e));
                objectOutputStream.close();
            } finally {
                socket.close();
            }
        }

        private void processMessage(FilePrinter filePrinter, String messagePackage) throws PrinterException {
            //IDEA adds one more slash for each escaping regexp slash!
            //
            //1. split different messages by two slashes
            // "/message1//message2/" = "/message1/" + "/message2/"
            //(?<!\\) - check that leading character not an escaping backslash
            String[] messages = messagePackage.split("(?<!\\\\)(\\/\\/)");

            int messagesCount = messages.length;
            for (int i = 0; i < messagesCount; i++) {
                String singleMessage = messages[i];
                //if messages received properly, then first and last character
                //in first and last message should
                //be "/", if not, don't process first and/or last string
                if (singleMessage.isEmpty() ||
                        (i == 0 && singleMessage.charAt(0) != '/') ||
                        (i == messagesCount - 1 && messages[messagesCount - 1].charAt(singleMessage.length() - 1) != '/')) {
                    continue;
                }
                //remove all single slashes "/" that not escaped with backslash "\"
                singleMessage = singleMessage.replaceAll("(?<!\\\\)(\\/)", "");
                //remove all escaping slashes
                singleMessage = singleMessage.replaceAll("(\\\\/)", "/");
                //send message to FilePrinter
                filePrinter.print(singleMessage);
            }
        }
    }
}
