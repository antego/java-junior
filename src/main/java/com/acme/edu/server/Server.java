package com.acme.edu.server;


import com.acme.edu.printer.FilePrinter;
import com.acme.edu.printer.PrinterException;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    FilePrinter filePrinter;

    public Server(FilePrinter filePrinter) {
        this.filePrinter = filePrinter;
    }

    private void startLogging() {
        try (ServerSocket ss = new ServerSocket(1337)) {
            Socket client = ss.accept();

            DataInputStream is = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            String readLine;
            while (true) {
                try {
                    readLine = is.readUTF();
                    if (readLine.equals("stop logger")) {
                        return;
                    }
                    this.filePrinter.print(readLine);
                } catch (EOFException e) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                } catch (PrinterException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
