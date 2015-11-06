package com.acme.edu.state;

import com.acme.edu.printer.Printer;
import com.acme.edu.printer.PrinterException;

/**
 * Класс объекта, управляющего переключением состояний логера
 */
public class StateManager {
    Printer printer;

    /**
     * Конструктор создает экземпляр класса StateManager с указанным Printer.
     *
     * @param printer Объект, который отвечает за сохранение или передачу логируемых данных.
     */
    public StateManager(Printer printer) {
        if (printer == null) {
            throw new NullPointerException("Printer object is null!");
        }
        this.printer = printer;
    }

    /**
     * Метод возвращает требуемое состояние, объект которого передается в качестве второго аргумента.
     *
     * @param currentState Текущее состояние логера.
     * @param wantedState  Требуемое состояние логера.
     * @return Требуемое состояние логера.
     * @throws PrinterException
     */
    public State getWantedState(State currentState, State wantedState) throws PrinterException {
        //если текущее состояние не инициализированно, просто возвращаем требуемое состояние с установленным принтером
        if (currentState == null) {
            wantedState.setPrinter(printer);
            return wantedState;
        }
        //Если состояния совпадают, возвращается текущее состояние
        if (currentState.getClass() == wantedState.getClass()) {
            return currentState;
            //Если нет, то у текущего сбрасывается буффер и возвращается требуемое состояние
        } else {
            if (currentState instanceof Flushable) {
                ((Flushable) currentState).flushBuffer();
            }
            wantedState.setPrinter(printer);
            return wantedState;
        }
    }
}
