package com.acme.edu.unit;

import com.acme.edu.printer.Printer;
import com.acme.edu.state.BlankState;
import com.acme.edu.state.IntState;
import com.acme.edu.state.StringState;
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
        IntState intState = new IntState(printer);
        //endregion

        //region when
        //region CheckThatSumsAndFlushesOnChangeStates
        intState.processMessage("1", s -> "primitive: " + s);
        intState.processMessage("1", s -> "primitive: " + s);
        intState.getBlankState();

        intState.processMessage("12", s -> "primitive: " + s);
        intState.getStringState();

        intState.processMessage("5", s -> "primitive: " + s);
        intState.getIntState(); //Call to getIntState doesn't flush buffer
        intState.processMessage("5", s -> "primitive: " + s);
        intState.getBlankState();
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
        IntState intState = new IntState(printer);
        //endregion

        //region when
        intState.processMessage("10", s -> "primitive: " + s);
        intState.processMessage(Integer.MAX_VALUE + "", s -> "primitive: " + s);
        intState.getBlankState();
        //endregion

        verify(printer).print("primitive: 10");
        verify(printer).print("primitive: " + Integer.MAX_VALUE);
    }

    @Test
    public void hasStringStateTest() {
        //region given
        StringState StringState = new StringState(printer);
        //endregion

        //region when
        StringState.processMessage("testString", s -> "string: " + s);
        StringState.getBlankState();

        StringState.processMessage("testString22", s -> "string: " + s);
        StringState.processMessage("testString22", s -> "string: " + s);
        StringState.getIntState();

        StringState.processMessage("testString33", s -> "string: " + s);
        StringState.processMessage("testString33", s -> "string: " + s);
        StringState.getStringState();
        StringState.processMessage("testString33", s -> "string: " + s);
        StringState.getBlankState();
        //endregion

        verify(printer).print("string: testString");
        verify(printer).print("string: testString22 (x2)");
        verify(printer).print("string: testString33 (x3)");
    }

    @Test
    public void BlankStateTest() {
        //region given
        BlankState blankState = new BlankState(printer);
        //endregion

        //region when
        blankState.processMessage("f", s -> "char: " + s);
        blankState.getBlankState();
        //endregion

        verify(printer).print("char: f");
    }
}
