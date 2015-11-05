package com.acme.edu.state;

import com.acme.edu.printer.Printer;
import com.acme.edu.printer.PrinterException;

public class StateManager {
    Printer printer;

    public StateManager(Printer printer) {
        if (printer == null) {
            throw new NullPointerException("Printer object is null!");
        }
        this.printer = printer;
    }

    public State getWantedState(State currentState, State wantedState) throws PrinterException {
        if (currentState == null) {
            wantedState.setPrinter(printer);
            return wantedState;
        }
        if (currentState.getClass() == wantedState.getClass()) {
            return currentState;
        } else {
            currentState.flushBuffer();
            wantedState.setPrinter(printer);
            return wantedState;
        }
    }
}
