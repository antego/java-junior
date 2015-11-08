package com.acme.edu.printer;

import com.acme.edu.server.ServerException;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class RemotePrinter implements Printer, Closeable {
    private Socket socket;

    private BufferedWriter bufferedWriter;
    private InputStream socketInputStream;

    private int messageBufferSize;

    /**
     * Crates new instance of RemotePrinter.
     *
     * @throws PrinterException
     */
    public RemotePrinter() throws PrinterException {
        try {
            socket = createSocket();
            OutputStreamWriter dataOutputStream = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
            bufferedWriter = new BufferedWriter(dataOutputStream);
            socketInputStream = socket.getInputStream();
        } catch (Exception e) {
            throw new PrinterException(e);
        }
    }

    /**
     * Method that can be overridden for test purposes
     *
     * @return instance of socket
     * @throws IOException
     */
    protected Socket createSocket() throws IOException {
        return new Socket("localhost", 31337);
    }

    /**
     * Sends messages to remote log server.
     *
     * @param message formatted message to print.
     * @throws PrinterException
     */
    @Override
    public void print(String message) throws PrinterException {
        messageBufferSize++;
        try {
            bufferedWriter.write(formRequest(message));
            if (messageBufferSize >= 50) {
                bufferedWriter.flush();
                messageBufferSize = 0;
            }
            if (socketInputStream.available() > 0) {
                try (ObjectInputStream serverExceptionInputStream = new ObjectInputStream(socketInputStream)) {
                    throw (ServerException) serverExceptionInputStream.readObject();
                }
            }
        } catch (Exception e) {
            throw new PrinterException(e);
        }
    }

    @Override
    public void close() throws PrinterException {
        try {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socketInputStream != null) {
                socketInputStream.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            throw new PrinterException(e);
        }
    }

    private String formRequest(String message) {
        return "/" + message.replace("/", "\\/") + "/";
    }
}
