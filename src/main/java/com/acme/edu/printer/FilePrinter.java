package com.acme.edu.printer;

import com.acme.edu.logger.Logger;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by anton on 06.11.15.
 */
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
