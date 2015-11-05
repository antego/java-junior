package com.acme.edu.unit;

import com.acme.edu.printer.Printer;
import com.acme.edu.state.NoBufferState;
import com.acme.edu.state.IntBufferState;
import com.acme.edu.state.StringBufferState;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StatesTest {

    private Printer printer;

    @Before
    public void setUpTests() {
        printer = mock(Printer.class);
    }

    @Test
    public void hasIntStateTest() {
        //region given
        IntBufferState intBufferState = new IntBufferState();
        intBufferState.setPrinter(printer);
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
        verify(printer).print("primitive: 2");
        verify(printer).print("primitive: 12");
        verify(printer).print("primitive: 10");
        //endregion

    }

    @Test
    public void hasIntStateOverflowTest() {
        //region given
        IntBufferState intBufferState = new IntBufferState();
        intBufferState.setPrinter(printer);
        //endregion

        //region when
        intBufferState.processMessage("10", "primitive: ");
        intBufferState.processMessage(Integer.MAX_VALUE + "", "primitive: ");
        intBufferState.flushBuffer();
        intBufferState.processMessage("-10", "primitive: ");
        intBufferState.processMessage(Integer.MIN_VALUE + "", "primitive: ");
        intBufferState.flushBuffer();
        //endregion

        verify(printer).print("primitive: 10");
        verify(printer).print("primitive: " + Integer.MAX_VALUE);
        verify(printer).print("primitive: -10");
        verify(printer).print("primitive: " + Integer.MIN_VALUE);
    }

    @Test
    public void hasStringStateTest() {
        //region given
        StringBufferState stringBufferState = new StringBufferState();
        stringBufferState.setPrinter(printer);
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

        verify(printer).print("string: testString");
        verify(printer).print("string: testString22 (x2)");
        verify(printer).print("string: testString33 (x3)");
    }

    @Test
    public void BlankStateTest() {
        //region given
        NoBufferState noBufferState = new NoBufferState();
        noBufferState.setPrinter(printer);
        //endregion

        //region when
        noBufferState.processMessage("f", "char: ");
        noBufferState.flushBuffer();
        //endregion

        verify(printer).print("char: f");
    }
}
