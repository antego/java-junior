package com.acme.edu;

/**
 * Printer to print logs in console
 */
public class SerialPrinter implements Printer {
    /**
     * Prints message in console.
     *
     * @param message message which must be printed in console.
     */
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
