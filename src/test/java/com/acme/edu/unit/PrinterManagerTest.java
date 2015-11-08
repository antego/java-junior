package com.acme.edu.unit;

import com.acme.edu.printer.Closeable;
import com.acme.edu.printer.FilePrinter;
import com.acme.edu.printer.Printer;
import com.acme.edu.printer.PrinterException;
import com.acme.edu.printer.PrinterManager;
import com.acme.edu.printer.PrinterManagerException;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

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

    @Test(expected = PrinterManagerException.class)
    public void shouldThrowPrinterManagerExceptionOnPrint() throws Exception {
        Printer faultyPrinter = mock(Printer.class);
        doThrow(PrinterException.class).when(faultyPrinter).print(anyString());

        printerManager = new PrinterManager(faultyPrinter);
        printerManager.print("");
    }

    @Test(expected = PrinterManagerException.class)
    public void shouldThrowPrinterManagerExceptionOnClose() throws Exception {
        Printer faultyPrinter = mock(FilePrinter.class);
        doThrow(PrinterException.class).when((Closeable) faultyPrinter).close();

        printerManager = new PrinterManager(faultyPrinter);
        printerManager.closePrinters();
    }
}
