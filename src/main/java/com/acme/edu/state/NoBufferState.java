package com.acme.edu.state;

import com.acme.edu.printer.Printer;
import com.acme.edu.printer.PrinterException;

/**
 * State that Logger has when previous message not an int, byte or String.
 */
public class NoBufferState implements State {
    private Printer printer;

    /**
     * Method prints input message through the Printer.
     *
     * @param message message to log.
     */
    @Override
    public void processMessage(String message, String prefix) throws PrinterException {
        printer.print(prefix + message);
    }

    @Override
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}