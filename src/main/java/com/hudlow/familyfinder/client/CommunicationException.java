package com.hudlow.familyfinder.client;

import java.io.IOException;

public class CommunicationException extends Exception {

    public CommunicationException(int httpErrorCode) {
        super("Server responded with HTTP error code: " + httpErrorCode);
    }

    public CommunicationException(String message) {
        super(message);
    }

    public CommunicationException(Exception exception) {
        super(exception);
    }

    public CommunicationException(String message, Exception cause) {
        super(message, cause);
    }
}
