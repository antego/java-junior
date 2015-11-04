package com.acme.edu.state;

import com.acme.edu.printer.Printer;
import com.acme.edu.decorator.Decorator;

/**
 * State that logger has if previous input message is of type string.
 */
public class StringState implements State {
    private String buffer;
    private int stringCount;
    private Printer printer;
    private Decorator decorator;

    /**
     * Creates new instance of {@code IntState} with specified printer.
     *
     * @param printer Printer instance.
     * @see Printer#print(String)
     */
    public StringState(Printer printer) {
        this.printer = printer;
    }

    /**
     * Method that processes and logs input string message.
     *
     * @param message message to log.
     */
    @Override
    public void processMessage(String message, Decorator decorator) {
        this.decorator = decorator;
        if (message.equals(buffer)) {
            stringCount++;
        } else {
            flushBuffer();
            buffer = message;
            stringCount = 1;
        }
    }

    @Override
    public BlankState getBlankState() {
        flushBuffer();
        return new BlankState(printer);
    }

    @Override
    public IntState getIntState() {
        flushBuffer();
        return new IntState(printer);
    }

    private void flushBuffer() {
        if (stringCount == 0) {
            return;
        }
        if (stringCount == 1) {
            printer.print(decorator.decorateMessage(buffer));
        } else {
            printer.print(decorator.decorateMessage(buffer + " (x" + stringCount + ")"));
        }
        stringCount = 0;
        buffer = null;
    }

    @Override
    public StringState getStringState() {
        return this;
    }

}
