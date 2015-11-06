package com.acme.edu.printer;

import com.acme.edu.logger.Logger;

import java.io.*;
import java.nio.charset.Charset;


public class FilePrinter implements Printer, CloseablePrinter {
    private String outputFileName;
    private Charset charset;

    private FileOutputStream fileOutputStream;
    private OutputStreamWriter logPrintWriter;

    private int messageCount;

    public FilePrinter(String outputFileName, Charset charset) throws PrinterException {
        this.outputFileName = outputFileName;
        this.charset = charset;

        try {
            fileOutputStream = new FileOutputStream(outputFileName, true);
        } catch (FileNotFoundException e) {
            throw new PrinterException(e);
        }
        logPrintWriter = new OutputStreamWriter(new BufferedOutputStream(fileOutputStream), charset);
    }

    @Override
    public void print(String message) throws PrinterException {
        try {
            logPrintWriter.write(message + Logger.SEP);
            messageCount++;
            if (messageCount == 50) {
                logPrintWriter.flush();
                messageCount = 0;
            }
        } catch (IOException e) {
            throw new PrinterException(e);
        }
    }

    @Override
    public void close() throws PrinterException {
        try {
            logPrintWriter.close();
        } catch (IOException e) {
            throw new PrinterException(e);
        }
    }
}
