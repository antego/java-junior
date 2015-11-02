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
            printer.print(buffer);
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
        printer.print("string: " + buffer + " (x" + stringCount + ")");
        stringCount = 0;
        buffer = null;
    }

}
