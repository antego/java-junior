package com.acme.edu.state;

import com.acme.edu.printer.Printer;
import com.acme.edu.printer.PrinterException;

/**
 * Class that manages switching states.
 */
public class StateManager {
    Printer printer;

    /**
     * Constructor creates new instance of StateManager with specified printer object.
     *
     * @param printer object that saves logs.
     */
    public StateManager(Printer printer) {
        if (printer == null) {
            throw new NullPointerException("Printer object is null!");
        }
        this.printer = printer;
    }

    /**
     * Method that returns wanted state with respect to the current state.
     *
     * @param currentState current state of the logger.
     * @param wantedState state that logger wants to have.
     * @return new state.
     * @throws PrinterException
     */
    public State getWantedState(State currentState, State wantedState) throws PrinterException {
        //if current state is null then just return wanted state with specified printer
        if (currentState == null) {
            wantedState.setPrinter(printer);
            return wantedState;
        }
        //if current state is the same as the wanted state, then return same current state
        if (currentState.getClass() == wantedState.getClass()) {
            return currentState;
        } else {
            //if not, then flush current state if it flushable and return wanted state with specified printer
            if (currentState instanceof Flushable) {
                ((Flushable) currentState).flushBuffer();
            }
            wantedState.setPrinter(printer);
            return wantedState;
        }
    }
}
