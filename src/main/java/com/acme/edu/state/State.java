package com.acme.edu.state;

import com.acme.edu.decorator.Decorator;

/**
 * State interface represents state of the Logger.
 */
public interface State {
    /**
     * Processes message according to a current state.
     *
     * @param message message to log.
     */
    void processMessage(String message, Decorator decorator);

    /**
     * Returns BlankState and flushes the buffer if needed.
     *
     * @return BlankState state.
     */
    State getBlankState();

    /**
     * Returns IntState and flushes the buffer if needed.
     *
     * @return IntState state.
     */
    State getIntState();

    /**
     * Returns StringState and flushes the buffer if needed.
     *
     * @return StringState state.
     */
    State getStringState();
}
