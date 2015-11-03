package com.acme.edu;

/**
 * State that Logger has if previous input message was byte or int.
 */
public class HasIntState implements State {
    private int buffer;
    private Printer printer;

    /**
     * Creates new instance of {@code HasIntState} with specified printer.
     *
     * @param printer Printer instance.
     * @see Printer#print(String)
     */
    public HasIntState(Printer printer) {
        this.printer = printer;
    }

    /**
     * Method that processes and logs input int or byte message.
     *
     * @param message message to log.
     */
    @Override
    public void processMessage(String message) {
        if (((long) Integer.parseInt(message) + buffer) > Integer.MAX_VALUE) {
            flushBuffer();
            buffer = Integer.parseInt(message);
        } else {
            buffer += Integer.parseInt(message);
        }
    }

    /**
     * Method flushes the message buffer. Must be called when non-int or byte message comes.
     */
    @Override
    public void flushBuffer() {
        printer.print(Logger.PRIMITIVE_PREFIX + buffer + "");
        buffer = 0;
    }

    @Override
    public BlankState giveMeBlankState() {
        flushBuffer();
        return new BlankState(printer);
    }

    @Override
    public HasIntState giveMeHasIntState() {
        return this;
    }

    @Override
    public HasStringState giveMeHasStringState() {
        flushBuffer();
        return new HasStringState(printer);
    }
}
