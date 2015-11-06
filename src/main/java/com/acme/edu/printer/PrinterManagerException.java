package com.acme.edu.printer;

import java.util.ArrayList;

public class PrinterManagerException extends Exception {
    private ArrayList<PrinterException> innerExceptions = new ArrayList<>();

    public PrinterManagerException() {
        super();
    }

    public PrinterManagerException(String message) {
        super(message);
    }

    public PrinterManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrinterManagerException(Throwable cause) {
        super(cause);
    }

    public void addPrinterException(PrinterException printerException) {
        innerExceptions.add(printerException);
    }

    public ArrayList<PrinterException> getPrinterExceptions() {
        return innerExceptions;
    }


}
