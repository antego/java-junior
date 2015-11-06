package com.acme.edu.unit;

import com.acme.edu.printer.Printer;
import com.acme.edu.printer.PrinterManager;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PrinterManagerTest {
    Printer firstPrinter;
    Printer secondPrinter;
    PrinterManager printerManager;

    @Before
    public void setUpTests() {
        firstPrinter = mock(Printer.class);
        secondPrinter = mock(Printer.class);
        printerManager = new PrinterManager(firstPrinter, secondPrinter);
    }

    @Test
    public void shouldCallPrintMethodOnAllPrinters() throws Exception {
        printerManager.print("test message");

        verify(firstPrinter).print("test message");
        verify(secondPrinter).print("test message");
    }
}
