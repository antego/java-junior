package com.acme.edu.unit;

import com.acme.edu.printer.PrinterException;
import com.acme.edu.printer.RemotePrinter;
import com.acme.edu.server.ServerException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RemotePrinterTest {
    Socket testSocket;
    Socket testSocketWithServerException;

    ByteArrayOutputStream byteArrayOutputStream;
    ByteArrayInputStream byteArrayInputStream;
    ByteArrayInputStream byteArrayInputStreamWithServerException;

    RemotePrinter testRemotePrinter;
    RemotePrinter testRemotePrinterThatThrowsServerException;

    @Before
    public void setUpRemotePrinter() throws Exception {
        testSocket = mock(Socket.class);
        byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayInputStream = new ByteArrayInputStream(new byte[]{});

        when(testSocket.getOutputStream()).thenReturn(byteArrayOutputStream);
        when(testSocket.getInputStream()).thenReturn(byteArrayInputStream);

        testRemotePrinter = new RemotePrinter() {
            @Override
            protected Socket createSocket() throws IOException {
                return testSocket;
            }
        };
    }

    @Before
    public void setUpRemotePrinterThatThrowsServerException() throws Exception {
        testSocketWithServerException = mock(Socket.class);

        byteArrayOutputStream = new ByteArrayOutputStream();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos)) {
            objectOutputStream.writeObject(new ServerException("Server not Ok"));
            byteArrayInputStreamWithServerException = new ByteArrayInputStream(baos.toByteArray());
        }

        when(testSocketWithServerException.getOutputStream()).thenReturn(byteArrayOutputStream);
        when(testSocketWithServerException.getInputStream()).thenReturn(byteArrayInputStreamWithServerException);

        testRemotePrinterThatThrowsServerException = new RemotePrinter() {
            @Override
            protected Socket createSocket() throws IOException {
                return testSocketWithServerException;
            }
        };
    }

    @After
    public void tearDown() throws Exception {
        testRemotePrinter.close();
        testRemotePrinterThatThrowsServerException.close();
        testSocket.close();
        testSocketWithServerException.close();
    }

    @Test
    public void shouldSendMessageToSocket() throws Exception {
        testRemotePrinter.print("test message");
        testRemotePrinter.close();

        byte[] outputStreamContent = byteArrayOutputStream.toByteArray();
        byte[] expectedContent = "/test message/".getBytes(StandardCharsets.UTF_8);
        Assert.assertTrue(Arrays.equals(outputStreamContent, expectedContent));
    }

    @Test
    public void shouldFlushBufferAfterFiftyMessages() throws Exception {
        for (int i = 0; i < 50; i++) {
            testRemotePrinter.print("");
        }

        Assert.assertTrue(new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8).contains("/"));
    }

    @Test
    public void shouldThrowDeserializedServerException() throws Exception {
        try {
            testRemotePrinterThatThrowsServerException.print("");
        } catch (PrinterException e) {
            Assert.assertTrue(e.getCause().getMessage().equals("Server not Ok"));
        }
    }
}
