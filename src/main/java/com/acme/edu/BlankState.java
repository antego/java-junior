package com.acme.edu;


public class BlankState implements State {
    private Printer printer;

    public BlankState(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void processMessage(String message) {
        printer.print(message);
    }

    @Override
    public void flushBuffer() {

    }
}
