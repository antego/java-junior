package com.acme.edu.unit;


import com.acme.edu.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.*;


public class LoggerTest {
    State state;
    Logger logger;

    @Before
    public void setUpTest() {
        state = mock(State.class);
        when(state.giveMeHasIntState()).thenReturn(state);
        when(state.giveMeHasStringState()).thenReturn(state);
        when(state.giveMeBlankState()).thenReturn(state);
        logger = new Logger(state);
    }

    @Test
    public void shouldPrintSumOfIntegers() {
        //region when
        logger.log(1);
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("1");
        //endregion
    }

    @Test
    public void shouldPrintSumOfStrings() {
        //region when
        logger.log("String");
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("String");
        //endregion
    }

    @Test
    public void shouldPrintSumOf() {
        //region when
        logger.log(new int[][]{{0}});
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("primitives array: {" + Logger.SEP + "{0}" + Logger.SEP + "}");
        //endregion
    }
}
