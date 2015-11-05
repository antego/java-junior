package com.acme.edu.state;

import com.acme.edu.printer.Printer;

/**
 * State that logger has if previous input message is of type string.
 */
public class StringBufferState implements State {
    private String buffer;
    private int stringCount;
    private Printer printer;
    private String prefix;

    /**
     * Creates new instance of {@code IntState} with specified printer.
     *
     * @param printer Printer instance.
     * @see Printer#print(String)
     */
    public StringBufferState(Printer printer) {
        this.printer = printer;
    }

    /**
     * Method that processes and logs input string message.
     *
     * @param message message to log.
     */
    @Override
    public void processMessage(String message, String prefix) {
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
    public NoBufferState getNoBufferState() {
        flushBuffer();
        return new NoBufferState(printer);
    }

    @Override
    public IntBufferState getIntBufferState() {
        flushBuffer();
        return new IntBufferState(printer);
    }

    private void flushBuffer() {
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
    public StringBufferState getStringBufferState() {
        return this;
    }

}
