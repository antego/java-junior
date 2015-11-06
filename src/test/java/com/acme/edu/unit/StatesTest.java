package com.acme.edu.unit;

import com.acme.edu.printer.Printer;
import com.acme.edu.printer.PrinterManager;
import com.acme.edu.state.NoBufferState;
import com.acme.edu.state.IntBufferState;
import com.acme.edu.state.StringBufferState;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StatesTest {

    private PrinterManager printerManager;

    @Before
    public void setUpTests() {
        printerManager = mock(PrinterManager.class);
    }

    @Test
    public void hasIntStateTest() throws Exception {
        //region given
        IntBufferState intBufferState = new IntBufferState();
        intBufferState.setPrinterManager(printerManager);
        //endregion

        //region when
        //region CheckThatSumsAndFlushesOnChangeStates
        intBufferState.processMessage("1", "primitive: ");
        intBufferState.processMessage("1", "primitive: ");
        intBufferState.flushBuffer();

        intBufferState.processMessage("12", "primitive: ");
        intBufferState.flushBuffer();

        intBufferState.processMessage("5", "primitive: ");
        intBufferState.processMessage("5", "primitive: ");
        intBufferState.flushBuffer();
        //endregion
        //endregion

        //region then
        verify(printerManager).print("primitive: 2");
        verify(printerManager).print("primitive: 12");
        verify(printerManager).print("primitive: 10");
        //endregion

    }

    @Test
    public void hasIntStateOverflowTest() throws Exception {
        //region given
        IntBufferState intBufferState = new IntBufferState();
        intBufferState.setPrinterManager(printerManager);
        //endregion

        //region when
        intBufferState.processMessage("10", "primitive: ");
        intBufferState.processMessage(Integer.MAX_VALUE + "", "primitive: ");
        intBufferState.flushBuffer();
        intBufferState.processMessage("-10", "primitive: ");
        intBufferState.processMessage(Integer.MIN_VALUE + "", "primitive: ");
        intBufferState.flushBuffer();
        //endregion

        verify(printerManager).print("primitive: 10");
        verify(printerManager).print("primitive: " + Integer.MAX_VALUE);
        verify(printerManager).print("primitive: -10");
        verify(printerManager).print("primitive: " + Integer.MIN_VALUE);
    }

    @Test
    public void hasStringStateTest() throws Exception {
        //region given
        StringBufferState stringBufferState = new StringBufferState();
        stringBufferState.setPrinterManager(printerManager);
        //endregion

        //region when
        stringBufferState.processMessage("testString", "string: ");
        stringBufferState.flushBuffer();

        stringBufferState.processMessage("testString22", "string: ");
        stringBufferState.processMessage("testString22", "string: ");
        stringBufferState.flushBuffer();

        stringBufferState.processMessage("testString33", "string: ");
        stringBufferState.processMessage("testString33", "string: ");
        stringBufferState.processMessage("testString33", "string: ");
        stringBufferState.flushBuffer();
        //endregion

        verify(printerManager).print("string: testString");
        verify(printerManager).print("string: testString22 (x2)");
        verify(printerManager).print("string: testString33 (x3)");
    }

    @Test
    public void BlankStateTest() throws Exception {
        //region given
        NoBufferState noBufferState = new NoBufferState();
        noBufferState.setPrinterManager(printerManager);
        //endregion

        //region when
        noBufferState.processMessage("f", "char: ");
        //endregion

        verify(printerManager).print("char: f");
    }
}
