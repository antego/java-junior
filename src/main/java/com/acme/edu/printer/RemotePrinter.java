package com.acme.edu.printer;

import com.acme.edu.server.ServerException;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RemotePrinter implements Printer, Closeable {
    private Socket socket;

    private BufferedWriter bufferedWriter;
    private InputStream socketInputStream;

    //ArrayList is chosen because Collections.sort() calls method toArray()
    //and works with array. Method toArray() in ArrayList relies on System.arraycopy().
    //It's faster than creating new array and iterating through a LinkedList
    private List<String> messageBuffer = new ArrayList<>();

    /**
     * Crates new instance of RemotePrinter.
     *
     * @throws PrinterException
     */
    public RemotePrinter(Socket socket) throws PrinterException {
        try {
            this.socket = socket;
            OutputStreamWriter dataOutputStream = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
            bufferedWriter = new BufferedWriter(dataOutputStream);
            socketInputStream = socket.getInputStream();
        } catch (Exception e) {
            throw new PrinterException(e);
        }
    }

    /**
     * Sends messages to remote log server.
     *
     * @param message formatted message to print.
     * @throws PrinterException
     */
    @Override
    public void print(String message) throws PrinterException {
        messageBuffer.add(message);
        try {
            if (messageBuffer.size() >= 50) {
                flushLocalBuffer();
                bufferedWriter.flush();
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
            flushLocalBuffer();
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

    private void flushLocalBuffer() throws IOException {
        Collections.sort(messageBuffer, (s1, s2) -> s1.compareTo("ERROR") - s2.compareTo("ERROR"));
        for (String singleMessage : messageBuffer) {
            bufferedWriter.write(formRequest(singleMessage));
        }
        messageBuffer.clear();
    }

    private String formRequest(String message) {
        return "/" + message.replace("/", "\\/") + "/";
    }
}
