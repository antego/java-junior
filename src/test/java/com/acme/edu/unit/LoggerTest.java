package com.acme.edu.unit;


import com.acme.edu.logger.LogException;
import com.acme.edu.logger.Logger;
import com.acme.edu.printer.PrinterException;
import com.acme.edu.state.State;
import com.acme.edu.state.StateManager;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class LoggerTest {
    State state;
    Logger logger;

    State printerExceptionState;
    Logger printerExceptionLogger;

    @Before
    public void setUpTest() throws Exception {
        state = mock(State.class);
        StateManager stateManager = mock(StateManager.class);
        logger = new Logger(stateManager);
        when(stateManager.getWantedState(anyObject(), anyObject())).thenReturn(state);
    }

    @Before
    public void setUpTestForExceptionHandling() throws Exception {
        printerExceptionState = mock(State.class);
        StateManager stateManager = mock(StateManager.class);
        printerExceptionLogger = new Logger(stateManager);
        when(stateManager.getWantedState(anyObject(), anyObject())).thenThrow(PrinterException.class);
    }

    @Test
    public void shouldPrintObject() throws Exception {
        //region when
        Object stubObject = new Object();
        logger.log(stubObject);
        //endregion

        //region then
        verify(state).processMessage(stubObject + "", "reference: ");
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

    //region log method should throw LoggerException if message is null
    @Test(expected = LogException.class)
    public void shouldThrowExceptionOnNullStringArray() throws Exception {
        logger.log((String[]) null);
    }

    @Test(expected = LogException.class)
    public void shouldThrowExceptionOnNullString() throws Exception {
        logger.log((String) null);
    }

    @Test(expected = LogException.class)
    public void shouldThrowExceptionOnNullIntArray() throws Exception {
        logger.log((int[]) null);
    }

    @Test(expected = LogException.class)
    public void shouldThrowExceptionOnNullIntMatrix() throws Exception {
        logger.log((int[][]) null);
    }

    @Test(expected = LogException.class)
    public void shouldThrowExceptionOnNullObject() throws Exception {
        logger.log((Object) null);
    }

    @Test(expected = LogException.class)
    public void shouldThrowExceptionOnNullMultimatrix() throws Exception {
        logger.log((int[][][][]) null);
    }
    //endregion

    //region logger should rethrow printerManager exception
    @Test(expected = PrinterException.class)
    public void shouldThrowPrinterExceptionOnObject() throws Exception {
        printerExceptionLogger.log(new Object());
    }

    @Test(expected = PrinterException.class)
    public void shouldThrowPrinterExceptionOnStopLogging() throws Exception {
        printerExceptionLogger.stopLogging();
    }

    @Test(expected = PrinterException.class)
    public void shouldThrowPrinterExceptionOnInt() throws Exception {
        printerExceptionLogger.log(1337);
    }

    @Test(expected = PrinterException.class)
    public void shouldThrowPrinterExceptionOnBoolean() throws Exception {
        printerExceptionLogger.log(true);
    }

    @Test(expected = PrinterException.class)
    public void shouldThrowPrinterExceptionOnChar() throws Exception {
        printerExceptionLogger.log('c');
    }

    @Test(expected = PrinterException.class)
    public void shouldThrowPrinterExceptionOnString() throws Exception {
        printerExceptionLogger.log("Stringh");
    }

    @Test(expected = PrinterException.class)
    public void shouldThrowPrinterExceptionOnIntArray() throws Exception {
        printerExceptionLogger.log(new int[]{0});
    }

    @Test(expected = PrinterException.class)
    public void shouldThrowPrinterExceptionOnIntMatrix() throws Exception {
        printerExceptionLogger.log(new int[][]{{0}});
    }

    @Test(expected = PrinterException.class)
    public void shouldThrowPrinterExceptionOnIntMultimatrix() throws Exception {
        printerExceptionLogger.log(new int[][][][]{{{{0}}}});
    }
    //endregion
}