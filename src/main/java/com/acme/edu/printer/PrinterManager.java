package com.acme.edu.printer;

/**
 * Printer manager that manages many different printers
 */
public class PrinterManager {
    Printer[] printers;

    /**
     * Constructor that creates instance of printer manager with specified printers
     *
     * @param printers array of Printer objects.
     */
    public PrinterManager(Printer... printers) {
        this.printers = printers;
    }


    /**
     * Method that calls print() methods on each printer
     *
     * @param message message to log
     * @throws PrinterException
     */
    public void print(String message) throws PrinterException {
        for (Printer printer : printers) {
            printer.print(message);
        }
    }
}
