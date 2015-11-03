package com.acme.edu.unit;


import com.acme.edu.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;


public class LoggerTest {
    State state;
    DecoratorFactory decorators;
    Logger logger;

    @Before
    public void setUpTest() {
        state = mock(State.class);
        when(state.giveMeHasIntState()).thenReturn(state);
        when(state.giveMeHasStringState()).thenReturn(state);
        when(state.giveMeBlankState()).thenReturn(state);
        decorators = new Decorators();
        logger = new Logger(state, decorators);
    }

    @Test
    public void shouldPrintSumOfIntegers() {
        //region when
        logger.log(1);
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("1", decorators.getIntDecorator());
        //endregion
    }

    @Test
    public void shouldPrintSumOfStrings() {
        //region when
        logger.log("String");
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("String", decorators.getStringDecorator());
        //endregion
    }

    @Test
    public void shouldPrintSumOf() {
        //region when
        logger.log(new int[][]{{0}});
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("{" + Logger.SEP + "{0}" + Logger.SEP + "}", decorators.getIntTwoDimensionalArrayDecorator());
        //endregion
    }
}
