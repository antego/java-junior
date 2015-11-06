package com.acme.edu.state;

import com.acme.edu.printer.PrinterManager;
import com.acme.edu.printer.PrinterManagerException;

/**
 * State that logger has if previous input message is of type string.
 */
public class StringBufferState implements State, Flushable {
    private String buffer;
    private int stringCount;
    private PrinterManager printerManager;
    private String prefix;

    /**
     * Method that processes and logs input string message.
     *
     * @param message message to log.
     */
    @Override
    public void processMessage(String message, String prefix) throws PrinterManagerException {
        this.prefix = prefix;
        if (message.equals(buffer)) {
            stringCount++;
        } else {
            flushBuffer();
            buffer = message;
            stringCount = 1;
        }
    }

    @Override
    public void flushBuffer() throws PrinterManagerException {
        if (stringCount == 0) {
            return;
        }
        if (stringCount == 1) {
            printerManager.print(prefix + buffer);
        } else {
            printerManager.print(prefix + buffer + " (x" + stringCount + ")");
        }
        stringCount = 0;
        buffer = null;
    }

    @Override
    public void setPrinterManager(PrinterManager printerManager) {
        this.printerManager = printerManager;
    }
}
