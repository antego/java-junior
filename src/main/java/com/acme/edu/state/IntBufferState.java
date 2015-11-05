package com.acme.edu.state;

import com.acme.edu.printer.Printer;

/**
 * State that Logger has if previous input message was byte or int.
 */
public class IntBufferState implements State {
    private int buffer;
    private Printer printer;
    private String prefix;

    /**
     * Method that processes and logs input int or byte message.
     *
     * @param message message to log.
     */
    @Override
    public void processMessage(String message, String prefix) {
        this.prefix = prefix;
        if (((long) Integer.parseInt(message) + buffer) > Integer.MAX_VALUE || ((long) Integer.parseInt(message) + buffer) < Integer.MIN_VALUE) {
            flushBuffer();
            buffer = Integer.parseInt(message);
        } else {
            buffer += Integer.parseInt(message);
        }
    }

    @Override
    public void flushBuffer() {
        printer.print(prefix + buffer + "");
        buffer = 0;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}
