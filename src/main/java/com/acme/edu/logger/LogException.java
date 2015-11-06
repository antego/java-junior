package com.acme.edu.logger;

/**
 * Exception that threw by the Logger if it fails logging operation.
 */
public class LogException extends Exception {
    /**
     * Default constructor.
     */
    public LogException() {
        super();
    }

    /**
     * Constructor with message.
     *
     * @param message message with additional information.
     */
    public LogException(String message) {
        super(message);
    }

    /**
     * Constructor with message.
     *
     * @param message message with additional information.
     * @param cause   throwable, that causes throwing this exception.
     */
    public LogException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with cause.
     *
     * @param cause throwable, that causes throwing this exception.
     */
    public LogException(Throwable cause) {
        super(cause);
    }
}