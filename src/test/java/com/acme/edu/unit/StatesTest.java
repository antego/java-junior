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
        IntBufferState intBufferState = new IntBufferState(printer);
        //endregion

        //region when
        //region CheckThatSumsAndFlushesOnChangeStates
        intBufferState.processMessage("1", "primitive: ");
        intBufferState.processMessage("1", "primitive: ");
        intBufferState.getBlankState();

        intBufferState.processMessage("12", "primitive: ");
        intBufferState.getStringState();

        intBufferState.processMessage("5", "primitive: ");
        intBufferState.getIntState(); //Call to getIntState doesn't flush buffer
        intBufferState.processMessage("5", "primitive: ");
        intBufferState.getBlankState();
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
        IntBufferState intBufferState = new IntBufferState(printer);
        //endregion

        //region when
        intBufferState.processMessage("10", "primitive: ");
        intBufferState.processMessage(Integer.MAX_VALUE + "", "primitive: ");
        intBufferState.getBlankState();
        //endregion

        verify(printer).print("primitive: 10");
        verify(printer).print("primitive: " + Integer.MAX_VALUE);
    }

    @Test
    public void hasStringStateTest() {
        //region given
        StringBufferState StringBufferState = new StringBufferState(printer);
        //endregion

        //region when
        StringBufferState.processMessage("testString", "string: ");
        StringBufferState.getBlankState();

        StringBufferState.processMessage("testString22", "string: ");
        StringBufferState.processMessage("testString22", "string: ");
        StringBufferState.getIntState();

        StringBufferState.processMessage("testString33", "string: ");
        StringBufferState.processMessage("testString33", "string: ");
        StringBufferState.getStringState();
        StringBufferState.processMessage("testString33", "string: ");
        StringBufferState.getBlankState();
        //endregion

        verify(printer).print("string: testString");
        verify(printer).print("string: testString22 (x2)");
        verify(printer).print("string: testString33 (x3)");
    }

    @Test
    public void BlankStateTest() {
        //region given
        NoBufferState noBufferState = new NoBufferState(printer);
        //endregion

        //region when
        noBufferState.processMessage("f", "char: ");
        noBufferState.getBlankState();
        //endregion

        verify(printer).print("char: f");
    }
}
