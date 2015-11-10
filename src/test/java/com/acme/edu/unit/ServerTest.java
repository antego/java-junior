package com.acme.edu.unit;


import com.acme.edu.printer.FilePrinter;
import com.acme.edu.printer.PrinterException;
import com.acme.edu.server.Server;
import com.acme.edu.server.ServerException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.*;

public class ServerTest {
    Server server;
    private ByteArrayOutputStream byteArrayOutputStream;
    private ByteArrayInputStream byteArrayInputStream;
    private FilePrinter stubFilePrinter;


    @Before
    public void setUpServer() throws Exception {
        ServerSocket stubServerSocket = mock(ServerSocket.class);
        Socket stubSocket = mock(Socket.class);
        stubFilePrinter = mock(FilePrinter.class);
        byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayInputStream = new ByteArrayInputStream("/test_messa\\/ge/".getBytes());

        when(stubServerSocket.accept()).thenReturn(stubSocket).thenReturn(null);
        when(stubSocket.getOutputStream()).thenReturn(byteArrayOutputStream);
        when(stubSocket.getInputStream()).thenReturn(byteArrayInputStream);

        server = new Server(stubFilePrinter, stubServerSocket);
    }

    @After
    public void tearDown() throws Exception {
        byteArrayInputStream.close();
        byteArrayOutputStream.close();
        stubFilePrinter.close();
    }

    @Test
    public void shouldCallFilePrinterWithSpecifiedString() throws Exception {
        try {
            server.startLogServer();
        } catch (ServerException e) {

        }
        verify(stubFilePrinter).print("test_messa/ge");
    }

    @Test
    public void shouldSerializeServerException() throws Exception {
        doThrow(PrinterException.class).when(stubFilePrinter).print(anyString());

        try {
            server.startLogServer();
        } catch (ServerException e) {

        }
        ByteArrayInputStream objectByteInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ServerException serializedServerException = (ServerException) new ObjectInputStream(objectByteInputStream).readObject();
        Assert.assertTrue(serializedServerException.getCause() instanceof PrinterException);
    }

    @Test
    public void shouldNotLogIncompleteMessages()throws Exception {
        ServerSocket stubServerSocket = mock(ServerSocket.class);
        Socket stubSocket = mock(Socket.class);
        stubFilePrinter = mock(FilePrinter.class);
        byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayInputStream = new ByteArrayInputStream("test_message".getBytes());

        when(stubServerSocket.accept()).thenReturn(stubSocket).thenReturn(null);
        when(stubSocket.getOutputStream()).thenReturn(byteArrayOutputStream);
        when(stubSocket.getInputStream()).thenReturn(byteArrayInputStream);

        server = new Server(stubFilePrinter, stubServerSocket);

        verifyZeroInteractions(stubFilePrinter);
    }

}
