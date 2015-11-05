package com.acme.edu.unit;


import com.acme.edu.*;
import com.acme.edu.state.State;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class LoggerTest {
    State state;
    Logger logger;

    @Before
    public void setUpTest() {
        state = mock(State.class);
        when(state.getIntBufferState()).thenReturn(state);
        when(state.getStringBufferState()).thenReturn(state);
        when(state.getNoBufferState()).thenReturn(state);
        logger = new Logger(state);
    }

    @Test
    public void shouldPrintObject() {
        //region when
        Object stubObject = null;
        //stubObject is null so there is no call to state.processMessage()
        logger.log(stubObject);
        stubObject = new Object();
        logger.log(stubObject);
        //endregion

        //region then
        verify(state).processMessage(stubObject.toString(), "reference: ");
        //endregion
    }

    @Test
    public void shouldPrintChar() {
        logger.log('c');

        verify(state).processMessage("c", "char: ");
    }

    @Test
    public void shouldPrintBoolean() {
        logger.log(true);

        verify(state).processMessage("true", "primitive: ");
    }

    @Test
    public void shouldPrintByte() {
        logger.log((byte) -8);

        verify(state).processMessage("-8", "primitive: ");
    }

    @Test
    public void shouldPrintInteger() {
        //region when
        logger.log(1);
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("1", "primitive: ");
        //endregion
    }

    @Test
    public void shouldPrintString() {
        //region when
        logger.log((String) null);
        logger.log("String");
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("String", "string: ");
        //endregion
    }

    @Test
    public void shouldPrintTwoDimensionalArrayDump() {
        //region when
        logger.log((int[][]) null);
        logger.log(new int[][]{{0}});
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("{" + Logger.SEP + "{0}" + Logger.SEP + "}", "primitives matrix: ");
        //endregion
    }

    @Test
    public void shouldLogSumOfArrayElements() {
        //region when
        logger.log((int[]) null);
        logger.log(new int[]{0, 1, 2, 3, 4});
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("10", "primitives array: ");
        //endregion
    }


    @Test
    public void shouldLogFourDimensionalArray() {
        //region when
        logger.log(new int[][][][]{{{{0, 1}}}});
        logger.log((int[][][][]) null);
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("{" + Logger.SEP +
                "{" + Logger.SEP +
                "{" + Logger.SEP +
                "{0, 1}" + Logger.SEP +
                "}" + Logger.SEP +
                "}" + Logger.SEP +
                "}", "primitives multimatrix: ");
        //endregion
    }

    @Test
    public void shouldLogStringArray() {
        //region when
        logger.log((String[]) null);
        logger.log("string1", "string2", "string3");
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("string1", "");
        verify(state).processMessage("string2", "");
        verify(state).processMessage("string3", "");
        //endregion
    }
}
