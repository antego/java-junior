package com.acme.edu;

/**
 * A printer that prints message to the specified destination.
 */
public interface Printer {
    /**
     * Prints input message to the specified destination.
     *
     * @param message formatted message to print.
     */
    void print(String message);
}
