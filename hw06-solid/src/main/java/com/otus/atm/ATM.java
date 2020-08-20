package com.otus.atm;

import com.otus.atm.exceptions.TakingOutException;

import java.util.Collection;

public interface ATM {

    void add(BankBill... bills);

    void add(Collection<? extends BankBill> bills);

    Collection<? extends BankBill> takeOut(long amount) throws TakingOutException;

    long getBalance();

}
