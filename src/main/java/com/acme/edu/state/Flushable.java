package com.acme.edu.state;

import com.acme.edu.printer.PrinterManagerException;

/**
 * Flushable interface that denotes that implementation has buffer
 */
public interface Flushable {
    /**
     * Method that flushes buffer
     *
     * @throws PrinterManagerException
     */
    void flushBuffer() throws PrinterManagerException;
}
