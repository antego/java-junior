package com.acme.edu.unit;

import com.acme.edu.BlankState;
import com.acme.edu.HasIntState;
import com.acme.edu.HasStringState;
import com.acme.edu.Printer;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StatesTest {
    @Test
    public void hasIntStateTest() {
        //region given
        Printer printer = mock(Printer.class);
        HasIntState state = new HasIntState(printer);
        //endregion

        //region when
        state.processMessage("1");
        state.processMessage("1");
        state.flushBuffer();
        //endregion

        verify(printer).print("primitive: 2");
    }

    @Test
    public void hasStringStateTest() {
        //region given
        Printer printer = mock(Printer.class);
        HasStringState state = new HasStringState(printer);
        //endregion

        //region when
        state.processMessage("testString");
        state.processMessage("testString");
        state.flushBuffer();
        //endregion

        verify(printer).print("string: testString (x2)");
    }

    @Test
    public void BlankStateTest() {
        //region given
        Printer printer = mock(Printer.class);
        BlankState state = new BlankState(printer);
        //endregion

        //region when
        state.processMessage("char: f");
        state.flushBuffer();
        //endregion

        verify(printer).print("char: f");
    }
}
