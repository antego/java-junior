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
     * @throws PrinterManagerException
     */
    public void print(String message) throws PrinterManagerException {
        PrinterManagerException printerManagerException = new PrinterManagerException();
        for (Printer printer : printers) {
            try {
                printer.print(message);
            } catch (PrinterException e) {
                printerManagerException.addPrinterException(e);
            }
        }
        if (printerManagerException.getPrinterExceptions().size() > 0) {
            throw printerManagerException;
        }
    }

    public void closePrinters() throws PrinterManagerException {
        PrinterManagerException printerManagerException = new PrinterManagerException();
        for (Printer printer : printers) {
            if (printer instanceof CloseablePrinter) {
                try {
                    ((CloseablePrinter) printer).close();
                } catch (PrinterException e) {
                    printerManagerException.addPrinterException(e);
                }
            }
        }
        if (printerManagerException.getPrinterExceptions().size() > 0) {
            throw printerManagerException;
        }
    }
}
