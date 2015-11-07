package com.acme.edu.printer;

import com.acme.edu.logger.Logger;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class RemotePrinter implements Printer, Closeable {
    Socket socket;
    OutputStreamWriter dataOutputStream;
    ArrayList<String> messageBuffer = new ArrayList<>();


    public RemotePrinter() throws PrinterException {
        try {
            socket = new Socket("127.0.0.1", 1337);
            dataOutputStream = new OutputStreamWriter(new BufferedOutputStream(socket.getOutputStream()), Charset.defaultCharset());
        } catch (Exception e) {
            throw new PrinterException(e);
        }
    }

    @Override
    public void print(String message) throws PrinterException {
        messageBuffer.add(message);
        StringBuilder serverResponse = new StringBuilder();
        try {
            dataOutputStream.write(message + Logger.SEP);
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream(), Charset.defaultCharset());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            long timeOfStart = System.currentTimeMillis();
            while(!bufferedReader.ready() || (timeOfStart > System.currentTimeMillis() - 3000));
            if(timeOfStart <= System.currentTimeMillis() - 3000) {
                throw new SocketTimeoutException();
            }
            serverResponse.append(bufferedReader.readLine());
        } catch (Exception e) {
            throw new PrinterException(e);
        }
    }

    @Override
    public void close() throws PrinterException {
        try {
            if (dataOutputStream != null) {
                dataOutputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            throw new PrinterException(e);
        }
    }
}
