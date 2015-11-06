package com.acme.edu.state;

import com.acme.edu.printer.PrinterException;
import com.acme.edu.printer.PrinterManager;

/**
 * Class that manages switching states.
 */
public class StateManager {
    PrinterManager printerManager;

    /**
     * Constructor creates new instance of StateManager with specified printerManager object.
     *
     * @param printerManager object that saves logs.
     */
    public StateManager(PrinterManager printerManager) {
        if (printerManager == null) {
            throw new NullPointerException("Printer manager object is null!");
        }
        this.printerManager = printerManager;
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
        //if current state is null then just return wanted state with specified printerManager
        if (currentState == null) {
            wantedState.setPrinterManager(printerManager);
            return wantedState;
        }
        //if current state is the same as the wanted state, then return same current state
        if (currentState.getClass() == wantedState.getClass()) {
            return currentState;
        } else {
            //if not, then flush current state if it flushable and return wanted state with specified printerManager
            if (currentState instanceof Flushable) {
                ((Flushable) currentState).flushBuffer();
            }
            wantedState.setPrinterManager(printerManager);
            return wantedState;
        }
    }
}
