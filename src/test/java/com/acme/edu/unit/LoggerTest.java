package com.acme.edu.unit;


import com.acme.edu.*;
import com.acme.edu.state.State;
import com.acme.edu.state.StateManager;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class LoggerTest {
    State state;
    StateManager stateManager;
    Logger logger;


    @Before
    public void setUpTest() throws Exception {
        state = mock(State.class);
        stateManager = mock(StateManager.class);
        logger = new Logger(stateManager);
        when(stateManager.getWantedState(anyObject(), anyObject())).thenReturn(state);
    }

    @Test
    @Ignore
    public void shouldPrintObject() throws Exception {
        //region when
        Object stubObject = new Object();
        logger.log(stubObject);
        //endregion

        //region then
        verify(state).processMessage(stubObject.toString(), "reference: ");
        //endregion
    }

    @Test
    public void shouldPrintChar() throws Exception {
        logger.log('c');

        verify(state).processMessage("c", "char: ");
    }

    @Test
    public void shouldPrintBoolean() throws Exception {
        logger.log(true);

        verify(state).processMessage("true", "primitive: ");
    }

    @Test
    public void shouldPrintByte() throws Exception {
        logger.log((byte) -8);

        verify(state).processMessage("-8", "primitive: ");
    }

    @Test
    public void shouldPrintInteger() throws Exception {
        //region when
        logger.log(1);
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("1", "primitive: ");
        //endregion
    }

    @Test
    public void shouldPrintString() throws Exception {
        //region when
        logger.log("String");
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("String", "string: ");
        //endregion
    }

    @Test
    public void shouldPrintTwoDimensionalArrayDump() throws Exception {
        //region when
        logger.log(new int[][]{{0}});
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("{" + Logger.SEP + "{0}" + Logger.SEP + "}", "primitives matrix: ");
        //endregion
    }

    @Test
    public void shouldLogSumOfArrayElements() throws Exception {
        //region when
        logger.log(new int[]{0, 1, 2, 3, 4});
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("10", "primitives array: ");
        //endregion
    }


    @Test
    public void shouldLogFourDimensionalArray() throws Exception {
        //region when
        logger.log(new int[][][][]{{{{0, 1}}}});
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
    public void shouldLogStringArray() throws Exception {
        //region when
        logger.log("string1", "string2", "string3");
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("string1", "");
        verify(state).processMessage("string2", "");
        verify(state).processMessage("string3", "");
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException() {
        logger.log((String[]) null);
    }
}
