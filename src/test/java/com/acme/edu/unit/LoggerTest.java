package com.acme.edu.unit;


import com.acme.edu.*;
import com.acme.edu.decorator.Decorator;
import com.acme.edu.decorator.DecoratorFactory;
import com.acme.edu.state.State;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;


public class LoggerTest {
    State state;
    DecoratorFactory decorators;
    Logger logger;
    Decorator decorator;

    @Before
    public void setUpTest() {
        state = mock(State.class);
        when(state.getIntState()).thenReturn(state);
        when(state.getStringState()).thenReturn(state);
        when(state.getBlankState()).thenReturn(state);
        decorator = mock(Decorator.class);
        //Factory stub that returns same Decorator instance for all calls to methods where return type is Decorator.
        decorators = mock(DecoratorFactory.class, (Answer) invocationOnMock -> {
            if (Decorator.class.equals(invocationOnMock.getMethod().getReturnType())) {
                return decorator;
            } else {
                return RETURNS_DEFAULTS.answer(invocationOnMock);
            }
        });

        logger = new Logger(state, decorators);
    }

    @Test
    public void shouldLogObject() {
        //region when
        Object stubObject = null;
        //stubObject is null so there is no call to state.processMessage()
        logger.log(stubObject);
        stubObject = new Object();
        logger.log(stubObject);
        //endregion

        //region then
        verify(state).processMessage(stubObject.toString(), decorator);
        //endregion
    }

    @Test
    public void shouldLogChar() {
        logger.log('c');

        verify(state).processMessage("c", decorator);
    }

    @Test
    public void shouldLogBoolean() {
        logger.log(true);

        verify(state).processMessage("true", decorator);
    }

    @Test
    public void shouldLogByte() {
        logger.log((byte) -8);

        verify(state).processMessage("-8", decorator);
    }

    @Test
    public void shouldPrintSumOfIntegers() {
        //region when
        logger.log(1);
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("1", decorator);
        //endregion
    }

    @Test
    public void shouldPrintSumOfStrings() {
        //region when
        logger.log("String");
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("String", decorator);
        //endregion
    }

    @Test
    public void shouldPrintTwoDimensionalArrayDump() {
        //region when
        logger.log(new int[][]{{0}});
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("{" + Logger.SEP + "{0}" + Logger.SEP + "}", decorator);
        //endregion
    }

    @Test
    public void shouldLogSumOfArrayElements() {
        //region when
        logger.log(new int[]{0, 1, 2, 3, 4});
        logger.stopLogging();
        //endregion

        //region then
        verify(state).processMessage("10", decorator);
        //endregion
    }


    @Test
    public void shouldLogFourDimensionalArray() {
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
                "}", decorator);
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
        verify(state).processMessage("string1", decorator);
        verify(state).processMessage("string2", decorator);
        verify(state).processMessage("string3", decorator);
        //endregion
    }
}
