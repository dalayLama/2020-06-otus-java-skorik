package com.otus.atm;

import java.util.Collection;

public interface CashBox {

    void add(BankBill... bills);

    void add(Collection<? extends BankBill> bills);

    Collection<? extends BankBill> takeAway(Collection<? extends BankBill> bills);

    Collection<? extends BankBill> getAll();

}
