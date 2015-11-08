package com.acme.edu.printer;

import com.acme.edu.logger.Logger;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Appender that implements saving log messages to a file.
 */
public class FilePrinter implements Printer, Closeable {
    private BufferedWriter logPrintWriter;

    private int messageCount;

    /**
     * Creates new instance of FilePrinter with file path and charset encoding.
     *
     * @param outputFileName file name of output log fie.
     * @param charset charset log messages.
     * @throws PrinterException
     */
    public FilePrinter(String outputFileName, Charset charset) throws PrinterException {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(outputFileName, true);
        } catch (FileNotFoundException e) {
            throw new PrinterException(e);
        }
        logPrintWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, charset));
    }

    @Override
    public void print(String message) throws PrinterException {
        try {
            logPrintWriter.write(message + Logger.SEP);
            messageCount++;
            if (messageCount >= 50) {
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
            if (logPrintWriter != null) {
                logPrintWriter.close();
            }
        } catch (IOException e) {
            throw new PrinterException(e);
        }
    }
}
