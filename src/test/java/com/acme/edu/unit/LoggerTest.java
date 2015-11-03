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
}
