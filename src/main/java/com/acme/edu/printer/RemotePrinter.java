package com.acme.edu.printer;

import com.acme.edu.server.ServerException;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class RemotePrinter implements Printer, Closeable {
    Socket socket;
    BufferedWriter bufferedWriter;

    InputStream socketInputStream;
    int messageBufferSize;


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

    protected Socket createSocket() throws IOException {
        return new Socket("127.0.0.1", 1337);
    }

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
                socketInputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            throw new PrinterException(e);
        }
    }

    private String formRequest(String message) {
        return "/PUT/" + message.replace("/", "\\/") + "/";
    }
}
