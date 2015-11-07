package com.acme.edu.printer;


public interface Closeable extends AutoCloseable {
    void close() throws PrinterException;
}
