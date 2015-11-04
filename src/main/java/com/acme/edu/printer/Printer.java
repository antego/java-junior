package com.acme.edu.printer;

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
