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
     * Returns BlankState and flushes the buffer if needed.
     *
     * @return BlankState state.
     */
    State giveMeBlankState();

    /**
     * Returns HasIntState and flushes the buffer if needed.
     *
     * @return HasIntState state.
     */
    State giveMeHasIntState();

    /**
     * Returns HasStringState and flushes the buffer if needed.
     *
     * @return HasStringState state.
     */
    State giveMeHasStringState();
}
