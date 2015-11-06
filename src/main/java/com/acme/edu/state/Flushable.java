package com.acme.edu.state;

import com.acme.edu.printer.PrinterException;

/**
 * Flushable interface that denotes that implementation has buffer
 */
public interface Flushable {
    /**
     * Method that flushes buffer
     *
     * @throws PrinterException
     */
    void flushBuffer() throws PrinterException;
}
