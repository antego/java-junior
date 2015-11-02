package com.acme.edu;

public class HasIntState implements State {
    private int buffer;
    private Printer printer;

    public HasIntState(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void processMessage(String message, MessageType messageType) {
        if (messageType != MessageType.INT && messageType != MessageType.BYTE) {
            flushBuffer();
            return;
        }
        if (messageType == MessageType.INT) {
            printNumericValue(Integer.parseInt(message), Integer.MAX_VALUE, printer);
        } else {
            printNumericValue(Byte.parseByte(message), Byte.MAX_VALUE, printer);
        }
    }

    @Override
    public void flushBuffer() {
        printer.print(Logger.PRIMITIVE_PREFIX + buffer + "");
        buffer = 0;
    }

    private void printNumericValue(int message, int maxValue, Printer printer) {
        if (((long) message + buffer) > maxValue) {
            flushBuffer();
            buffer = message;
        } else {
            buffer += message;
        }
    }
}
