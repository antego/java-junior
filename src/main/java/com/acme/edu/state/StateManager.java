package com.acme.edu.state;

import com.acme.edu.printer.Printer;

public class StateManager {
    Printer printer;

    public StateManager(Printer printer) throws NullPointerException {
        if (printer == null) {
            throw new NullPointerException("Printer object is null!");
        }
        this.printer = printer;
    }

    public State getWantedState(State currentState, State wantedState) {
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
