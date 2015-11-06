package com.acme.edu.state;

import com.acme.edu.printer.PrinterManager;
import com.acme.edu.printer.PrinterManagerException;

/**
 * State that Logger has if previous input message was byte or int.
 */
public class IntBufferState implements State, Flushable {
    private int buffer;
    private PrinterManager printerManager;
    private String prefix;

    /**
     * Method that processes and logs input int or byte message.
     *
     * @param message message to log.
     */
    @Override
    public void processMessage(String message, String prefix) throws PrinterManagerException {
        this.prefix = prefix;
        if (((long) Integer.parseInt(message) + buffer) > Integer.MAX_VALUE || ((long) Integer.parseInt(message) + buffer) < Integer.MIN_VALUE) {
            flushBuffer();
            buffer = Integer.parseInt(message);
        } else {
            buffer += Integer.parseInt(message);
        }
    }

    @Override
    public void flushBuffer() throws PrinterManagerException {
        printerManager.print(prefix + buffer + "");
        buffer = 0;
    }

    @Override
    public void setPrinterManager(PrinterManager printerManager) {
        this.printerManager = printerManager;
    }
}
