package com.acme.edu.server;


import com.acme.edu.printer.FilePrinter;
import com.acme.edu.printer.PrinterException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Server that logs input messages and saves them with the FilePrinter into file.
 */
public class Server {
    /**
     * Main loop. Will be ended after 15 seconds without input requests.
     * If exception arises in message processing procedure, then
     * exception will be serialized and sent back to the client.
     *
     * @throws Exception
     */
    public void startLogServer() throws Exception {
        try (FilePrinter filePrinter = createFilePrinter();
             ServerSocket serverSocket = createSocket()) {
            serverSocket.setSoTimeout(15*1000);
            while (true) {
                Socket socket = serverSocket.accept();
                try (InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
                     BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                    String message = "";
                    String line;
                    while ((line = bufferedReader.readLine()) == null) ; //wait for bytes
                    do {
                        message += line;
                    } while ((line = bufferedReader.readLine()) != null);
                    processMessage(filePrinter, message);
                } catch (Exception e) {
                    new ObjectOutputStream(socket.getOutputStream()).writeObject(new ServerException(e));
                } finally {
                    socket.close();
                }
            }
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
            if (singleMessage.isEmpty()) continue;
            //if messages received properly, then first and last character
            // in first and last message should
            //be "/", if not, don't process first and/or last string
            if (i == 0 && singleMessage.charAt(0) != '/') continue;
            if (i == messagesCount - 1 && messages[messagesCount - 1].charAt(singleMessage.length() - 1) != '/')
                continue;
            //remove all single slashes "/" that not escaped with backslash "\"
            singleMessage = singleMessage.replaceAll("(?<!\\\\)(\\/)", "");
            //remove all escaping slashes
            singleMessage = singleMessage.replaceAll("(\\\\\\/)", "");
            //send message to FilePrinter
            filePrinter.print(singleMessage);
        }
    }

    /**
     * Method that can be overridden for test purposes.
     *
     * @return FilePrinter in tests it will be stub.
     * @throws IOException
     */
    protected ServerSocket createSocket() throws IOException {
        return new ServerSocket(31337);
    }

    /**
     * Method that can be overridden for test purposes.
     *
     * @return FilePrinter in tests it will be stub.
     * @throws PrinterException
     */
    protected FilePrinter createFilePrinter() throws PrinterException {
        return new FilePrinter("server_test_log", StandardCharsets.UTF_8);
    }
}
