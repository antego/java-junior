package com.acme.edu.printer;

/**
 * Exception that threw by the printer if it fails.
 */
public class PrinterException extends Exception {
    /**
     * Default constructor.
     */
    public PrinterException() {
        super();
    }

    /**
     * Constructor with message.
     *
     * @param message message with additional information.
     */
    public PrinterException(String message) {
        super(message);
    }


    /**
     * Constructor with message.
     *
     * @param message message with additional information.
     * @param cause   throwable, that causes throwing this exception.
     */
    public PrinterException(String message, Throwable cause) {
        super(message, cause);
    }


    /**
     * Constructor with cause.
     *
     * @param cause throwable, that causes throwing this exception.
     */
    public PrinterException(Throwable cause) {
        super(cause);
    }
}
