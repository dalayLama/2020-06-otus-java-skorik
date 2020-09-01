package com.otus.atm.exceptions;

public class NotEnoughBillsException extends PickingUpException {

    public NotEnoughBillsException() {
    }

    public NotEnoughBillsException(String message) {
        super(message);
    }

    public NotEnoughBillsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughBillsException(Throwable cause) {
        super(cause);
    }

    public NotEnoughBillsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
