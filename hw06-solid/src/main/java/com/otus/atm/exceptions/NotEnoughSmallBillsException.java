package com.otus.atm.exceptions;

public class NotEnoughSmallBillsException extends PickingUpException {

    public NotEnoughSmallBillsException() {
    }

    public NotEnoughSmallBillsException(String message) {
        super(message);
    }

    public NotEnoughSmallBillsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughSmallBillsException(Throwable cause) {
        super(cause);
    }

    public NotEnoughSmallBillsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
