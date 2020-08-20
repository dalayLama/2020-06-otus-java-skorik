package com.otus.atm.impls;

import com.otus.atm.*;
import com.otus.atm.exceptions.TakingOutException;

import java.util.Collection;

public class DefaultATM implements ATM {

    private final CashBox cashBox;

    private final Calculator calculator;

    public DefaultATM(CashBox cashBox, Calculator calculator) {
        this.cashBox = cashBox;
        this.calculator = calculator;
    }

    @Override
    public void add(BankBill... bills) {
        cashBox.add(bills);
    }

    @Override
    public void add(Collection<? extends BankBill> bills) {
        cashBox.add(bills);
    }

    @Override
    public Collection<? extends BankBill> takeOut(long amount) throws TakingOutException {
        try {
            Collection<? extends BankBill> pickedBills = calculator.pickUpBillsForAmount(amount, cashBox.getAll());
            return cashBox.takeAway(pickedBills);
        } catch (Exception e) {
            throw new TakingOutException(e);
        }
    }

    @Override
    public long getBalance() {
        return calculator.sum(cashBox.getAll());
    }
}
