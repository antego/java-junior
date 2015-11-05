package com.acme.edu.state;

import com.acme.edu.printer.Printer;

/**
 * State that Logger has when previous message not an int, byte or String.
 */
public class NoBufferState implements State {
    private Printer printer;

    /**
     * Creates new instance of {@code BlankState} with specified printer.
     *
     * @param printer Printer instance.
     * @see Printer#print(String)
     */
    public NoBufferState(Printer printer) {
        this.printer = printer;
    }

    /**
     * Method prints input message through the Printer.
     *
     * @param message message to log.
     */
    @Override
    public void processMessage(String message, String prefix) {
        printer.print(prefix + message);
    }

    @Override
    public NoBufferState getNoBufferState() {
        return this;
    }

    @Override
    public IntBufferState getIntBufferState() {
        return new IntBufferState(printer);
    }

    @Override
    public StringBufferState getStringBufferState() {
        return new StringBufferState(printer);
    }
}
