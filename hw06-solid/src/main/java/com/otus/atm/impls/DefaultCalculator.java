package com.otus.atm.impls;

import com.otus.atm.BankBill;
import com.otus.atm.Calculator;
import com.otus.atm.exceptions.NotEnoughBillsException;
import com.otus.atm.exceptions.NotEnoughSmallBillsException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultCalculator implements Calculator {

    @Override
    public Collection<? extends BankBill> pickUpBillsForAmount(long amount, Collection<? extends BankBill> bills) {
        List<? extends BankBill> sorted = bills.stream()
                .sorted(Comparator.comparingInt(BankBill::getValue).reversed())
                .collect(Collectors.toList());
        List<BankBill> picked = pickUp(amount, sorted);
        long sum = sum(picked);

        if (sum == amount) {
            return picked;
        } else if (sum > amount || (sum == 0 && picked.isEmpty() && !bills.isEmpty())) {
            throw new NotEnoughSmallBillsException();
        } else {
            throw new NotEnoughBillsException();
        }
    }

    @Override
    public long sum(Collection<? extends BankBill> bills) {
        return bills.stream()
                .mapToInt(BankBill::getValue)
                .sum();
    }

    private List<BankBill> pickUp(long amount, Collection<? extends BankBill> bills) {
        List<BankBill> picked = new ArrayList<>();
        long sum = 0;
        for (BankBill bill : bills) {
            if (sum >= amount) {
                break;
            }
            if (bill.getValue() < amount) {
                picked.add(bill);
                sum += bill.getValue();
            }
        }
        return picked;
    }

}
