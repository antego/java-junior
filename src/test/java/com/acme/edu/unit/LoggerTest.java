package com.acme.edu.unit;


import com.acme.edu.Logger;
import com.acme.edu.Printer;
import org.junit.After;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class LoggerTest {
    @Test
    public void shouldPrintSumOfIntegers() {
        //region given
        Printer printer = mock(Printer.class);
        Logger logger = new Logger(printer);
        //endregion

        //region when
        logger.log(1);
        logger.log(1);
        logger.stopLogging();
        //endregion

        verify(printer).print("primitive: 2");
    }

    @Test
    public void shouldPrintSumOfStrings() {
        //region given
        Printer printer = mock(Printer.class);
        Logger logger = new Logger(printer);
        //endregion

        //region when
        logger.log("string");
        logger.log("string");
        logger.stopLogging();
        //endregion

        verify(printer).print("string: string (x2)");
    }

    @Test
    public void shouldPrintSumOf() {
        //region given
        Printer printer = mock(Printer.class);
        Logger logger = new Logger(printer);
        //endregion

        //region when
        logger.log(new int[][]{{0}});
        logger.stopLogging();
        //endregion

        verify(printer).print("primitives array: {"+ Logger.SEP + "{0}" + Logger.SEP + "}");
    }
}
