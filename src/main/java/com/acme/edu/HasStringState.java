package com.acme.edu;


public class HasStringState implements State {
    private String buffer;
    private int stringCount;
    private Printer printer;

    public HasStringState(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void processMessage(String message, MessageType messageType) {
        if(messageType != MessageType.STRING) {
            flushBuffer();
            return;
        }
        if(message.equals(buffer)) {
            stringCount++;
        } else {
            flushBuffer();
            buffer = message;
        }
    }

    @Override
    public void flushBuffer() {
        if(stringCount == 1) {
            printer.print("string: " + buffer);
        } else {
            printer.print("string: " + buffer + " (x" + stringCount + ")");
        }
        stringCount = 0;
        buffer = null;
    }

}
