package com.otus.atm;

import com.otus.atm.exceptions.PickingUpException;

import java.util.Collection;

public interface Calculator {

    Collection<? extends BankBill> pickUpBillsForAmount(long amount, Collection<? extends BankBill> bills) throws PickingUpException;

    long sum(Collection<? extends BankBill> bills);

}
