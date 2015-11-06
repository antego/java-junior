package com.acme.edu.printer;

import com.acme.edu.logger.Logger;

import java.io.DataOutputStream;
import java.net.Socket;

public class RemotePrinter implements Printer {
    @Override
    public void print(String message) throws PrinterException {
        try (Socket socket = new Socket("127.0.0.1", 1337);
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
            dataOutputStream.writeUTF(message + Logger.SEP);
        } catch (Exception e) {
            throw new PrinterException(e);
        }
    }
}
