package com.acme.edu.logger;

/**
 * Exception that threw by the Logger if message not correspond to requirements.
 */
public class IllegalArgumentException extends Exception {
    /**
     * Default constructor.
     */
    public IllegalArgumentException() {
        super();
    }

    /**
     * Constructor with message.
     *
     * @param message message with additional information.
     */
    public IllegalArgumentException(String message) {
        super(message);
    }

    /**
     * Constructor with message.
     *
     * @param message message with additional information.
     * @param cause   throwable, that causes throwing this exception.
     */
    public IllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with cause.
     *
     * @param cause throwable, that causes throwing this exception.
     */
    public IllegalArgumentException(Throwable cause) {
        super(cause);
    }
}
