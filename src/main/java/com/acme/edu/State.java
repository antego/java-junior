package com.acme.edu;

/**
 * State interface represents state of the Logger.
 */
public interface State {
    /**
     * Processes message according to a current state.
     *
     * @param message message to log.
     */
    void processMessage(String message);

    /**
     * Must print and reset buffer if it present.
     */
    void flushBuffer();

    BlankState giveMeBlankState();

    HasIntState giveMeHasIntState();

    HasStringState giveMeHasStringState();
}
