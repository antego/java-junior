package com.acme.edu.printer;

/**
 * Interface that is implemented by printers with close() method.
 */
public interface Closeable extends AutoCloseable {
    void close() throws PrinterException;
}
