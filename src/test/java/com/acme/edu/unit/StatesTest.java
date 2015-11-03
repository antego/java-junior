package com.acme.edu.unit;

import com.acme.edu.BlankState;
import com.acme.edu.HasIntState;
import com.acme.edu.HasStringState;
import com.acme.edu.Printer;
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
        hasIntState.processMessage("1", s -> "primitive: " + s);
        hasIntState.processMessage("1", s -> "primitive: " + s);
        hasIntState.giveMeBlankState();
        //endregion

        verify(printer).print("primitive: 2");
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
        //endregion

        verify(printer).print("string: testString (x2)");
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
