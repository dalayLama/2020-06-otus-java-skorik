package com.otus.atm;

public enum BankBill {

    _50(50), _100(100), _500(500), _1000(1000), _5000(5000);

    private final int value;

    BankBill(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
