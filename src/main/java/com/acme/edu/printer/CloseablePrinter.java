package com.acme.edu.printer;


public interface CloseablePrinter extends AutoCloseable {
    void close() throws PrinterException;
}
