package com.acme.edu.iteration01;

import com.acme.edu.*;
import com.acme.edu.logger.Logger;
import com.acme.edu.printer.SerialPrinter;
import com.acme.edu.state.StateManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static com.acme.edu.logger.Logger.SEP;

@Ignore
public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    Logger logger = new Logger(new StateManager(new SerialPrinter()));
    //region given
    @Before
    public void setUpSystemOut() throws Exception {
        resetOut();
        captureSysout();
    }
    //endregion

    @After
    public void tearDown() {
        resetOut();
    }

    @Test
    public void shouldLogInteger() throws Exception {
        //region when
        logger.log(1);
        logger.log(0);
        logger.log(-1);
        logger.stopLogging();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutEquals("primitive: 0" + SEP);
        //endregion
    }

    @Test
    public void shouldLogByte() throws Exception {
        //region when
        logger.log((byte) 1);
        logger.log((byte) 0);
        logger.log((byte) -1);
        logger.stopLogging();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("0");
        //endregion
    }

    @Test
    public void shouldLogChar() throws Exception {
        //region when
        logger.log('a');
        logger.log('b');
        logger.stopLogging();
        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }

    @Test
    public void shouldLogBoolean() throws Exception {
        //region when
        logger.log(true);
        logger.log(false);
        logger.stopLogging();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("true");
        assertSysoutContains("false");
        //endregion
    }

    @Test
    public void shouldLogString() throws Exception {
        //region when
        logger.log("test string 1");
        logger.log("other str");
        logger.stopLogging();
        //endregion

        //region then
        assertSysoutContains("string: ");
        assertSysoutContains("test string 1");
        assertSysoutContains("other str");
        //endregion
    }

    @Test
    public void shouldLogReference() throws Exception {
        //region when
        logger.log(new Object());
        logger.stopLogging();
        //endregion

        //region then
        assertSysoutContains("reference: ");
        assertSysoutContains("@");
        //endregion
    }
}