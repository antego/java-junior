package com.acme.edu;

/**
 * State that logger has if previous input message is of type string.
 */
public class HasStringState implements State {
    private String buffer;
    private int stringCount;
    private Printer printer;

    /**
     * Creates new instance of {@code HasStringState} with specified printer.
     *
     * @param printer Printer instance.
     * @see Printer#print(String)
     */
    public HasStringState(Printer printer) {
        this.printer = printer;
    }

    /**
     * Method that processes and logs input string message.
     *
     * @param message message to log.
     */
    @Override
    public void processMessage(String message) {
        if (message.equals(buffer)) {
            stringCount++;
        } else {
            flushBuffer();
            buffer = message;
            stringCount = 1;
        }
    }

    /**
     * Method flushes the message buffer. Must be called when non-String message comes.
     */
    @Override
    public void flushBuffer() {
        if (stringCount == 0) {
            return;
        }
        if (stringCount == 1) {
            printer.print("string: " + buffer);
        } else {
            printer.print("string: " + buffer + " (x" + stringCount + ")");
        }
        stringCount = 0;
        buffer = null;
    }

}
