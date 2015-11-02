package com.acme.edu;


public interface State {
    void processMessage(String message, MessageType messageType);

    void flushBuffer();
}
