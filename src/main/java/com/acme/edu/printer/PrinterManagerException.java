package com.acme.edu.printer;

import java.util.ArrayList;
import java.util.List;

/**
 * This exception is thrown by the PrinterManager and encapsulates exceptions from printers
 */
public class PrinterManagerException extends Exception {
    private final List<PrinterException> innerExceptions = new ArrayList<>();

    /**
     * Default constructor.
     */
    public PrinterManagerException() {
        super();
    }

    /**
     * Constructor with message.
     *
     * @param message message with additional information.
     */
    public PrinterManagerException(String message) {
        super(message);
    }

    /**
     * Constructor with message.
     *
     * @param message message with additional information.
     * @param cause   throwable, that causes throwing this exception.
     */
    public PrinterManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with cause.
     *
     * @param cause throwable, that causes throwing this exception.
     */
    public PrinterManagerException(Throwable cause) {
        super(cause);
    }

    /**
     * Adds printer exception
     *
     * @param printerException PrinterException that must be in the comprehensive exception.
     */
    public void addPrinterException(PrinterException printerException) {
        innerExceptions.add(printerException);
    }

    /**
     * Returns all inner exceptions
     *
     * @return list of PrinterExceptions
     */
    public List<PrinterException> getPrinterExceptions() {
        return innerExceptions;
    }


}
