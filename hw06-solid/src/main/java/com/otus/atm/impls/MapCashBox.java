package com.otus.atm.impls;

import com.otus.atm.BankBill;
import com.otus.atm.CashBox;
import com.otus.atm.exceptions.NotEnoughBillsException;

import java.util.*;

public class MapCashBox implements CashBox {

    private final Map<BankBill, Integer> bills = new TreeMap<>();

    @Override
    public void add(BankBill... bills) {
        add(List.of(bills));
    }

    @Override
    public void add(Collection<BankBill> bills) {
        sort(this.bills, bills);
    }

    @Override
    public Collection<BankBill> takeAway(Collection<BankBill> bills) {
        List<BankBill> removedBills = new ArrayList<>(bills.size());
        bills.forEach(b -> {
            if (this.bills.containsKey(b)) {
                Integer number = this.bills.get(b);
                if (number == null || number < 1) {
                    throw new NotEnoughBillsException("Not enough bank bills " + b.name());
                }
                this.bills.put(b, number - 1);
                removedBills.add(b);
            } else {
                throw new NoSuchElementException("Didn't find the bill " + b.name());
            }
        });
        return removedBills;
    }

    @Override
    public Collection<BankBill> getAll() {
        List<BankBill> bills = new ArrayList<>();
        this.bills.forEach((bankBill, integer) -> {
            for (int i = 0; i < integer; i++) {
                bills.add(bankBill);
            }
        });
        return bills;
    }

    private void sort(Map<BankBill, Integer> mapBills, Iterable<BankBill> bills) {
        bills.forEach(b -> mapBills.merge(b, 1, Integer::sum));
    }


}
