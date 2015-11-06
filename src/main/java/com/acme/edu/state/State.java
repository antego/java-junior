package com.acme.edu.state;

import com.acme.edu.printer.Printer;
import com.acme.edu.printer.PrinterException;
import com.acme.edu.printer.PrinterManager;

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
    void processMessage(String message, String prefix) throws PrinterException;

    /**
     * Метод для установки объекта, который будет записывать данные в постоянное хранилище
     *
     * @param printerManager объект, пишущий данные
     */
    void setPrinterManager(PrinterManager printerManager);
}
