package com.acme.edu.unit;


import com.acme.edu.*;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.*;

@Ignore
public class LoggerTest {
    @Test
    public void shouldPrintSumOfIntegers() {
        //region given
        HasIntState state = mock(HasIntState.class);
        when(state.giveMeHasIntState()).thenReturn(mock(HasIntState.class));
        Logger logger = new Logger(state);
        //endregion

        //region when
        logger.log(1);
        logger.log(1);
        logger.stopLogging();
        //endregion

        verify(state).processMessage("1");
    }

    @Test
    public void shouldPrintSumOfStrings() {
        //region given
        State state = mock(State.class);
        Logger logger = new Logger(state);
        //endregion

        //region when
        logger.log("String");
        logger.log(1);
        logger.stopLogging();
        //endregion

        verify(state).processMessage("String");
    }

    @Test
    public void shouldPrintSumOf() {
        //region given
        State state = mock(State.class);
        Printer printer = mock(Printer.class);
        when(state.giveMeBlankState()).thenReturn(new BlankState(printer));
        Logger logger = new Logger(state);
        //endregion

        //region when
        logger.log(new int[][]{{0}});
        logger.stopLogging();
        //endregion

        verify(state).processMessage("primitives array: {" + Logger.SEP + "{0}" + Logger.SEP + "}");
    }
}
