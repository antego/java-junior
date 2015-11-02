package com.acme.edu.iteration03;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.acme.edu.Logger.SEP;

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
    public void shouldLogIntegersArray() throws IOException {
        //region when
        Logger.log(new int[]{-1, 0, 1});
        Logger.stopLogging();
        //endregion

        //region then
        assertSysoutContains("0");
        //endregion
    }


    @Test
    public void shouldLogIntegersMatrix() throws IOException {
        //region when
        Logger.log(new int[][]{{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
        Logger.stopLogging();
        //endregion

        //region then
        assertSysoutEquals(
                "primitives array: {" + SEP +
                        "{-1, 0, 1}" + SEP +
                        "{1, 2, 3}" + SEP +
                        "{-1, -2, -3}" + SEP +
                        "}" + SEP
        );
        //endregion
    }


    @Test
    public void shouldLogIntegersMulitidimentionalArray() throws IOException {
        //region when
        Logger.log(new int[][][][]{{{{0}}}});
        Logger.stopLogging();
        //endregion

        //region then
        assertSysoutEquals(
                "primitives multimatrix: {" + SEP +
                        "{" + SEP + "{" + SEP + "{" +
                        "0" +
                        "}" + SEP + "}" + SEP + "}" + SEP +
                        "}" + SEP
        );
        //endregion
    }


    @Test
    public void shouldLogStringsWithOneMethodCall() throws IOException {
        //region when
        Logger.log("str1", "string 2", "str 3");
        Logger.stopLogging();
        //endregion

        //region then
        assertSysoutContains("str1" + SEP + "string 2" + SEP + "str 3");
        //endregion
    }

    @Test
    public void shouldLogIntegersWithOneMethodCall() throws IOException {
        //region when
        Logger.log(-1, 0, 1, 3);
        Logger.stopLogging();
        //endregion

        //region then
        assertSysoutContains("3");
        //endregion
    }

    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws IOException {
        //region when
        Logger.log(1);
        Logger.log("str");
        Logger.log(Integer.MAX_VALUE - 10);
        Logger.log(11);
        Logger.stopLogging();
        //endregion

        //region then
        assertSysoutContains("1");
        assertSysoutContains("str");
        assertSysoutContains(Integer.MAX_VALUE - 10 + "");
        assertSysoutContains("11");
        //endregion
    }
}