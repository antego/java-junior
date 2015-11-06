package com.acme.edu.server;

import com.acme.edu.logger.Logger;
import com.acme.edu.printer.Printer;
import com.acme.edu.printer.PrinterException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;


public class FilePrinter implements Printer {
    String outputFileName;
    Charset charset;

    public FilePrinter(String outputFileName, Charset charset) {
        this.outputFileName = outputFileName;
        this.charset = charset;
    }

    @Override
    public void print(String message) throws PrinterException {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(outputFileName, true);
                OutputStreamWriter logPrintWriter = new OutputStreamWriter(fileOutputStream, charset)
        ) {
            logPrintWriter.write(message + Logger.SEP);
        } catch (IOException e) {
            throw new PrinterException(e);
        }
    }
}
