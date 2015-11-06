package com.acme.edu.state;

import com.acme.edu.printer.PrinterException;

/**
 * Интерфейс, который должно реализовывать состояние, если у состояния есть буфер.
 */
public interface Flushable {
    /**
     * Метод записывает содержимое буфера и обнуляет его.
     * Необходимо вызвать этот метод при смене состояния.
     *
     * @throws PrinterException
     */
    void flushBuffer() throws PrinterException;
}
