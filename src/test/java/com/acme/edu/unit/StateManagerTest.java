package com.acme.edu.unit;


import com.acme.edu.printer.PrinterManager;
import com.acme.edu.state.IntBufferState;
import com.acme.edu.state.State;
import com.acme.edu.state.StateManager;
import com.acme.edu.state.StringBufferState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class StateManagerTest {
    PrinterManager printerManager;
    PrinterManager printerManagerThatThrowsException;
    StateManager stateManager;

    @Before
    public void setUpManager() {
        printerManager = mock(PrinterManager.class);
        stateManager = new StateManager(printerManager);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionInConstructor() {
        //not null parameter for full conditional coverage
        new StateManager(printerManager);
        //null parameter that causes exception
        new StateManager(null);
    }

    @Test
    public void shouldReturnWantedState() throws Exception {
        State currentState = null;

        currentState = stateManager.getWantedState(currentState, IntBufferState.class);

        Assert.assertEquals(currentState.getClass(), IntBufferState.class);
    }

    @Test
    public void shouldReturnCurrentState() throws Exception {
        State oldCurrentState = new IntBufferState();

        //if current state == wanted state then manager should return same current state
        State newCurrentState = stateManager.getWantedState(oldCurrentState, IntBufferState.class);

        Assert.assertEquals(oldCurrentState, newCurrentState);
    }

    @Test
    public void shouldReturnWantedStateAndFlushBuffer() throws Exception {
        State currentState = mock(IntBufferState.class);

        //if current state != wanted state then manager should return wanted state
        currentState = stateManager.getWantedState(currentState, StringBufferState.class);

        Assert.assertTrue(currentState instanceof StringBufferState);
    }
}
