package com.acme.edu.state;

import com.acme.edu.printer.PrinterManager;
import com.acme.edu.printer.PrinterManagerException;

/**
 * Интерфейс состояния, в котором может находится логер.
 */
public interface State {
    /**
     * Метод обрабатывает поступаемое сообщение
     *
     * @param message сообщение для логгирования
     * @param prefix префикс сообщения
     */
    void processMessage(String message, String prefix) throws PrinterManagerException;

    /**
     * Метод для установки объекта, который будет записывать данные в постоянное хранилище
     *
     * @param printerManager объект, пишущий данные
     */
    void setPrinterManager(PrinterManager printerManager);
}
