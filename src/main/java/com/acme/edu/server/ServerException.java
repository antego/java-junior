package com.acme.edu.server;

/**
 * Exception that is thrown by the server to the client
 */
public class ServerException extends Exception {
    /**
     * Default constructor.
     */
    public ServerException() {
        super();
    }

    /**
     * Constructor with message.
     *
     * @param message message with additional information.
     */
    public ServerException(String message) {
        super(message);
    }

    /**
     * Constructor with message.
     *
     * @param message message with additional information.
     * @param cause   throwable, that causes throwing this exception.
     */
    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with cause.
     *
     * @param cause throwable, that causes throwing this exception.
     */
    public ServerException(Throwable cause) {
        super(cause);
    }
}
