package com.acme.edu.state;

import com.acme.edu.printer.PrinterManager;
import com.acme.edu.printer.PrinterManagerException;

/**
 * State that Logger has when previous message not an int, byte or String.
 */
public class NoBufferState implements State {
    private PrinterManager printerManager;

    /**
     * Method prints input message through the Printer.
     *
     * @param message message to log.
     */
    @Override
    public void processMessage(String message, String prefix) throws PrinterManagerException {
        printerManager.print(prefix + message);
    }

    @Override
    public void setPrinterManager(PrinterManager printerManager) {
        this.printerManager = printerManager;
    }
}
