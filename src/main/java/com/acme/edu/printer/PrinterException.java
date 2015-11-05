package com.acme.edu.printer;

/**
 * Исключение, которое выбрасывается при ошибке в работе с ресурсом, куда записываются данные
 */
public class PrinterException extends Exception {
    public PrinterException() {
        super();
    }

    public PrinterException(String message) {
        super(message);
    }

    public PrinterException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrinterException(Throwable cause) {
        super(cause);
    }
}
