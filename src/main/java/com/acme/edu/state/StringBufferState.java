package com.acme.edu.state;

import com.acme.edu.printer.Printer;
import com.acme.edu.printer.PrinterException;

/**
 * State that logger has if previous input message is of type string.
 */
public class StringBufferState implements State {
    private String buffer;
    private int stringCount;
    private Printer printer;
    private String prefix;

    /**
     * Method that processes and logs input string message.
     *
     * @param message message to log.
     */
    @Override
    public void processMessage(String message, String prefix) throws PrinterException {
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
    public void flushBuffer() throws PrinterException {
        if (stringCount == 0) {
            return;
        }
        if (stringCount == 1) {
            printer.print(prefix + buffer);
        } else {
            printer.print(prefix + buffer + " (x" + stringCount + ")");
        }
        stringCount = 0;
        buffer = null;
    }

    @Override
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}
