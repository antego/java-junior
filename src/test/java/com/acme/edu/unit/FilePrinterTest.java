package com.acme.edu.unit;


import com.acme.edu.logger.Logger;
import com.acme.edu.printer.FilePrinter;
import com.acme.edu.printer.PrinterException;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;

public class FilePrinterTest {
    FilePrinter filePrinter;
    File testFile;

    @Before
    public void setUpTests() throws PrinterException {
        testFile = new File("test_file");
        testFile.delete();
        filePrinter = new FilePrinter(testFile.getPath(), Charset.defaultCharset());
    }

    @After
    public void tearDown() throws Exception{
        filePrinter.close();
        testFile.delete();
    }

    @Test
    public void shouldLogMessagesInFile() throws Exception {
        filePrinter.print("test string");
        filePrinter.close();

        Assert.assertEquals("test string" + Logger.SEP, FileUtils.readFileToString(testFile));
    }

    @Test
    public void shouldFlushBufferAfterFiftyMessages() throws Exception {
        StringBuilder contentOfTestFile = new StringBuilder();
        for(int i = 0; i < 50; i++) {
            filePrinter.print("test string" + i);
            contentOfTestFile.append("test string" + i + Logger.SEP);
        }

        Assert.assertEquals(contentOfTestFile.toString(), FileUtils.readFileToString(testFile));
    }

    @Test
    public void shouldThrowPrinterException() {

    }
}
