package com.acme.edu;

/**
 * Исключение, возникающее, если при логировании произошла ошибка
 */
public class LogException extends Exception {
    public LogException() {
        super();
    }

    public LogException(String message) {
        super(message);
    }

    public LogException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogException(Throwable cause) {
        super(cause);
    }
}