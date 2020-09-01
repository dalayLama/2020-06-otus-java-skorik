package com.otus.atm;

import com.otus.atm.exceptions.PickingUpException;

import java.util.Collection;

public interface Calculator {

    Collection<BankBill> pickUpBillsForAmount(long amount, Collection<BankBill> bills) throws PickingUpException;

    long sum(Collection<BankBill> bills);

}
