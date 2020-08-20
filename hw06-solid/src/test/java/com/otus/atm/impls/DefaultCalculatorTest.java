package com.otus.atm.impls;

import com.otus.atm.BankBill;
import com.otus.atm.exceptions.NotEnoughBillsException;
import com.otus.atm.exceptions.NotEnoughSmallBillsException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

class DefaultCalculatorTest {

    @Test
    void shouldSelectRightsBills() {
        DefaultCalculator calculator = new DefaultCalculator();
        Collection<? extends BankBill> bankBills = calculator.pickUpBillsForAmount(500, List.of(
                BankBill._100, BankBill._50, BankBill._100, BankBill._50, BankBill._1000,  BankBill._100, BankBill._100));

        assertThat(bankBills, contains(BankBill._100, BankBill._100, BankBill._100, BankBill._100, BankBill._50, BankBill._50));
    }

    @Test
    void shouldCalculateRightSum() {
        DefaultCalculator calculator = new DefaultCalculator();
        long sum = calculator.sum(List.of(
                BankBill._100, BankBill._50, BankBill._1000));
        Assertions.assertThat(100 + 50 + 1000).isEqualTo(sum);
    }

    @Test
    void shouldThrowNotEnoughBillsException() {
        DefaultCalculator calculator = new DefaultCalculator();
        org.junit.jupiter.api.Assertions.assertThrows(NotEnoughBillsException.class, () ->
                calculator.pickUpBillsForAmount(500, List.of(BankBill._100, BankBill._50)));
    }

    @Test
    void shouldThrowNotEnoughSmallBillsException() {
        DefaultCalculator calculator = new DefaultCalculator();
        org.junit.jupiter.api.Assertions.assertThrows(NotEnoughSmallBillsException.class, () ->
                calculator.pickUpBillsForAmount(50, List.of(BankBill._100, BankBill._500)));
    }

}