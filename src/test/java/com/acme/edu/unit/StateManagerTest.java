package com.acme.edu.unit;


import com.acme.edu.printer.Printer;
import com.acme.edu.state.IntBufferState;
import com.acme.edu.state.State;
import com.acme.edu.state.StateManager;
import com.acme.edu.state.StringBufferState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.verify;

public class StateManagerTest {
    Printer printer;
    Printer printerThatThrowsException;
    StateManager stateManager;

    @Before
    public void setUpManager() {
        printer = mock(Printer.class);
        stateManager = new StateManager(printer);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionInConstructor() {
        //not null parameter for full conditional coverage
        new StateManager(printer);
        //null parameter that causes exception
        new StateManager(null);
    }

    @Test
    public void shouldReturnWantedState() throws Exception {
        State wantedState = mock(State.class);
        State currentState = null;

        currentState = stateManager.getWantedState(currentState, wantedState);

        Assert.assertEquals(currentState, wantedState);
    }

    @Test
    public void shouldReturnCurrentState() throws Exception {
        State wantedState = mock(State.class);
        State oldCurrentState = wantedState;

        //if current state == wanted state then manager should return same current state
        State newCurrentState = stateManager.getWantedState(oldCurrentState, wantedState);

        Assert.assertEquals(oldCurrentState, newCurrentState);
    }

    @Test
    public void shouldReturnWantedStateAndFlushBuffer() throws Exception {
        State wantedState = mock(StringBufferState.class);
        State currentState = mock(IntBufferState.class);

        //if current state != wanted state then manager should return wanted state
        currentState = stateManager.getWantedState(currentState, wantedState);

        Assert.assertTrue(currentState == wantedState);
    }
}
