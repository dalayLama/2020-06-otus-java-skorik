package com.otus.atm;

import java.util.Collection;

public interface CashBox {

    void add(BankBill... bills);

    void add(Collection<BankBill> bills);

    Collection<BankBill> takeAway(Collection<BankBill> bills);

    Collection<BankBill> getAll();

}
