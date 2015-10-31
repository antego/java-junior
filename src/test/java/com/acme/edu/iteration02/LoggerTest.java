package com.acme.edu.iteration02;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when
        Logger.log("str 1");
        Logger.log(1);
        Logger.log(2);
        Logger.log("str 2");
        Logger.log(0);
        Logger.stopLogging();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1" + Logger.SEP +
                "primitive: 3" + Logger.SEP +
                "string: str 2" + Logger.SEP +
                "primitive: 0" + Logger.SEP
        );
        //endregion
    }

    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
        //region when
        Logger.log("str 1");
        Logger.log(10);
        Logger.log(Integer.MAX_VALUE);
        Logger.log("str 2");
        Logger.log(0);
        Logger.stopLogging();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1" + Logger.SEP +
                        "primitive: 10" + Logger.SEP +
                        "primitive: " + Integer.MAX_VALUE + Logger.SEP +
                        "string: str 2" + Logger.SEP +
                        "primitive: 0" + Logger.SEP
        );
        //endregion
    }

    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
        //region when
        Logger.log("str 1");
        Logger.log((byte)10);
        Logger.log(Byte.MAX_VALUE);
        Logger.log("str 2");
        Logger.log(0);
        Logger.stopLogging();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1" + Logger.SEP +
                "primitive: 10" + Logger.SEP +
                "primitive: " + Byte.MAX_VALUE + Logger.SEP +
                "string: str 2" + Logger.SEP +
                "primitive: 0" + Logger.SEP
        );
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
        //region when
        Logger.log("str 1");
        Logger.log("str 2");
        Logger.log("str 2");
        Logger.log(0);
        Logger.log("str 2");
        Logger.log("str 3");
        Logger.log("str 3");
        Logger.log("str 3");
        Logger.stopLogging();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1" + Logger.SEP +
            "string: str 2 (x2)" + Logger.SEP +
            "primitive: 0" + Logger.SEP +
            "string: str 2" + Logger.SEP +
            "string: str 3 (x3)" + Logger.SEP
        );
        //endregion
    }
}