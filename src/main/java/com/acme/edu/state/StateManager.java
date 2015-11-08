package com.acme.edu.state;

import com.acme.edu.printer.PrinterManager;
import com.acme.edu.printer.PrinterManagerException;

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
     * Close printer manager and all delegated printers.
     *
     * @throws PrinterManagerException
     */
    public void closePrinterManager() throws PrinterManagerException {
        printerManager.closePrinters();
    }

    /**
     * Method that returns wanted state with respect to the current state.
     *
     * @param currentState current state of the logger.
     * @param WantedState class of the state that logger wants to have.
     * @return new state.
     * @throws PrinterManagerException
     */
    public State getWantedState(State currentState, Class WantedState) throws PrinterManagerException {
        //if current state is null then just return wanted state with specified printerManager
        if (currentState == null) {
            return instantiateWantedState(WantedState);
        }
        //if current state is the same as the wanted state, then return same current state
        if (currentState.getClass() == WantedState) {
            return currentState;
        } else {
            //if not, then flush current state if it flushable and return wanted state with specified printerManager
            if (currentState instanceof Flushable) {
                ((Flushable) currentState).flushBuffer();
            }
            return instantiateWantedState(WantedState);
        }
    }

    private State instantiateWantedState(Class WantedState) throws PrinterManagerException {
        try {
            State wantedState = (State) WantedState.newInstance();
            wantedState.setPrinterManager(printerManager);
            return wantedState;
        } catch (Exception e) {
            throw new PrinterManagerException(e);
        }
    }
}
