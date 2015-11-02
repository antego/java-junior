package com.acme.edu;

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
    public void processMessage(String message) {
        printer.print(message);
    }

    /**
     * Method does nothing because of absence of buffer in this state.
     */
    @Override
    public void flushBuffer() {
        //Nothing to flush. Leaky abstraction.
    }
}
