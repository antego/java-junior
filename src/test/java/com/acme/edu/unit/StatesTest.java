package com.acme.edu.unit;

import com.acme.edu.*;
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
        HasIntState hasIntState = new HasIntState(printer);
        //endregion

        //region when
        //region CheckThatSumsAndFlushesOnChangeStates
        hasIntState.processMessage("1", s -> "primitive: " + s);
        hasIntState.processMessage("1", s -> "primitive: " + s);
        hasIntState.giveMeBlankState();

        hasIntState.processMessage("12", s -> "primitive: " + s);
        hasIntState.giveMeHasStringState();

        hasIntState.processMessage("5", s -> "primitive: " + s);
        hasIntState.giveMeHasIntState(); //Call to giveMeHasIntState doesn't flush buffer
        hasIntState.processMessage("5", s -> "primitive: " + s);
        hasIntState.giveMeBlankState();
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
        HasIntState hasIntState = new HasIntState(printer);
        //endregion

        //region when
        hasIntState.processMessage("10", s -> "primitive: " + s);
        hasIntState.processMessage(Integer.MAX_VALUE + "", s -> "primitive: " + s);
        hasIntState.giveMeBlankState();
        //endregion

        verify(printer).print("primitive: 10");
        verify(printer).print("primitive: " + Integer.MAX_VALUE);
    }

    @Test
    public void hasStringStateTest() {
        //region given
        HasStringState hasStringState = new HasStringState(printer);
        //endregion

        //region when
        hasStringState.processMessage("testString", s -> "string: " + s);
        hasStringState.processMessage("testString", s -> "string: " + s);
        hasStringState.giveMeBlankState();

        hasStringState.processMessage("testString22", s -> "string: " + s);
        hasStringState.processMessage("testString22", s -> "string: " + s);
        hasStringState.giveMeHasIntState();

        hasStringState.processMessage("testString33", s -> "string: " + s);
        hasStringState.processMessage("testString33", s -> "string: " + s);
        hasStringState.giveMeHasStringState();
        hasStringState.processMessage("testString33", s -> "string: " + s);
        hasStringState.giveMeBlankState();
        //endregion

        verify(printer).print("string: testString (x2)");
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
        blankState.giveMeBlankState();
        //endregion

        verify(printer).print("char: f");
    }
}
