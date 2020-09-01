package com.otus.atm;

import com.otus.atm.exceptions.TakingOutException;

import java.util.Collection;

public interface ATM {

    void add(BankBill... bills);

    void add(Collection<BankBill> bills);

    Collection<BankBill> takeOut(long amount) throws TakingOutException;

    long getBalance();

}
