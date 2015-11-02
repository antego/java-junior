package com.acme.edu;

public class HasIntState implements State {
    private int buffer;
    private Printer printer;

    public HasIntState(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void processMessage(String message) {
        if (((long) Integer.parseInt(message) + buffer) > Integer.MAX_VALUE) {
            flushBuffer();
            buffer = Integer.parseInt(message);
        } else {
            buffer += Integer.parseInt(message);
        }
    }

    @Override
    public void flushBuffer() {
        printer.print(Logger.PRIMITIVE_PREFIX + buffer + "");
        buffer = 0;
    }
}
