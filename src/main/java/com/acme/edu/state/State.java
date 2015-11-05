package com.acme.edu.state;

import com.acme.edu.printer.Printer;

/**
 * State interface represents state of the Logger.
 */
public interface State {
    /**
     * Processes message according to a current state.
     *
     * @param message message to log.
     */
    void processMessage(String message, String prefix);

    void setPrinter(Printer printer);

    void flushBuffer();
}
