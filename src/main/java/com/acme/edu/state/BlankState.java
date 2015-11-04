package com.acme.edu.state;

import com.acme.edu.printer.Printer;
import com.acme.edu.decorator.Decorator;

/**
 * State that Logger has when previous message not an int, byte or String.
 */
public class BlankState implements State {
    private Printer printer;

    /**
     * Creates new instance of {@code BlankState} with specified printer.
     *
     * @param printer Printer instance.
     * @see Printer#print(String)
     */
    public BlankState(Printer printer) {
        this.printer = printer;
    }

    /**
     * Method prints input message through the Printer.
     *
     * @param message message to log.
     */
    @Override
    public void processMessage(String message, Decorator decorator) {
        printer.print(decorator.decorateMessage(message));
    }

    @Override
    public BlankState getBlankState() {
        return this;
    }

    @Override
    public IntState getIntState() {
        return new IntState(printer);
    }

    @Override
    public StringState getStringState() {
        return new StringState(printer);
    }
}
