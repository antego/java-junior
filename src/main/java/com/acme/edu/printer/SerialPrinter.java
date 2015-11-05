package com.acme.edu.printer;

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
    public void print(String message) throws PrinterException {
        System.out.println(message);
    }
}
