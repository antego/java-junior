package com.acme.edu.state;

import com.acme.edu.printer.Printer;
import com.acme.edu.decorator.Decorator;

/**
 * State that Logger has if previous input message was byte or int.
 */
public class IntState implements State {
    private int buffer;
    private Printer printer;
    private Decorator decorator;

    /**
     * Creates new instance of {@code IntState} with specified printer.
     *
     * @param printer Printer instance.
     * @see Printer#print(String)
     */
    public IntState(Printer printer) {
        this.printer = printer;
    }

    /**
     * Method that processes and logs input int or byte message.
     *
     * @param message message to log.
     */
    @Override
    public void processMessage(String message, Decorator decorator) {
        this.decorator = decorator;
        if (((long) Integer.parseInt(message) + buffer) > Integer.MAX_VALUE) {
            flushBuffer();
            buffer = Integer.parseInt(message);
        } else {
            buffer += Integer.parseInt(message);
        }
    }

    @Override
    public BlankState getBlankState() {
        flushBuffer();
        return new BlankState(printer);
    }

    @Override
    public IntState getIntState() {
        return this;
    }

    @Override
    public StringState getStringState() {
        flushBuffer();
        return new StringState(printer);
    }

    private void flushBuffer() {
        printer.print(decorator.decorateMessage(buffer + ""));
        buffer = 0;
    }
}
