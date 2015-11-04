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
        intState.processMessage("1", "primitive: ");
        intState.processMessage("1", "primitive: ");
        intState.getBlankState();

        intState.processMessage("12", "primitive: ");
        intState.getStringState();

        intState.processMessage("5", "primitive: ");
        intState.getIntState(); //Call to getIntState doesn't flush buffer
        intState.processMessage("5", "primitive: ");
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
        intState.processMessage("10", "primitive: ");
        intState.processMessage(Integer.MAX_VALUE + "", "primitive: ");
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
        StringState.processMessage("testString", "string: ");
        StringState.getBlankState();

        StringState.processMessage("testString22", "string: ");
        StringState.processMessage("testString22", "string: ");
        StringState.getIntState();

        StringState.processMessage("testString33", "string: ");
        StringState.processMessage("testString33", "string: ");
        StringState.getStringState();
        StringState.processMessage("testString33", "string: ");
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
        blankState.processMessage("f", "char: ");
        blankState.getBlankState();
        //endregion

        verify(printer).print("char: f");
    }
}
