package com.quant.bridge.exception;

public class ClientOfflineException extends Exception {

    private static final long serialVersionUID = 1L;

    public ClientOfflineException() {
        super();
    }

    public ClientOfflineException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientOfflineException(String message) {
        super(message);
    }

    public ClientOfflineException(Throwable cause) {
        super(cause);
    }

}
