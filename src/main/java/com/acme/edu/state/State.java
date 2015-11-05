package com.acme.edu.state;

import com.acme.edu.printer.Printer;
import com.acme.edu.printer.PrinterException;

/**
 * State interface represents state of the Logger.
 */
public interface State {
    /**
     * Processes message according to a current state.
     *
     * @param message message to log.
     */
    void processMessage(String message, String prefix) throws PrinterException;

    void setPrinter(Printer printer);

    void flushBuffer() throws PrinterException;
}
