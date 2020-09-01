package com.otus.atm.impls;

import com.otus.atm.BankBill;
import com.otus.atm.CashBox;

import java.util.*;

public class ListCashBox implements CashBox {

    private final List<BankBill> bills = new ArrayList<>();

    @Override
    public void add(BankBill... bills) {
        add(List.of(bills));
    }

    @Override
    public void add(Collection<BankBill> bills) {
        this.bills.addAll(bills);
    }

    @Override
    public Collection<BankBill> takeAway(Collection<BankBill> bills) {
        List<BankBill> removedBills = new ArrayList<>(bills.size());
        bills.forEach(b -> {
            if (this.bills.contains(b)) {
                this.bills.remove(b);
                removedBills.add(b);
            } else {
                throw new NoSuchElementException("Didn't find the bill " + b.name());
            }
        });
        return removedBills;
    }

    @Override
    public Collection<BankBill> getAll() {
        return Collections.unmodifiableList(bills);
    }



}
