package com.otus.atm.impls;

import com.otus.atm.BankBill;
import com.otus.atm.Calculator;
import com.otus.atm.CashBox;
import com.otus.atm.exceptions.TakingOutException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

class DefaultATMTest {

    private CashBox cashBox;

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        cashBox = new ListCashBox();
        calculator = new DefaultCalculator();
    }

    @Test
    public void shouldCorrectWork() {
        DefaultATM atm = new DefaultATM(cashBox, calculator);
        atm.add(List.of(BankBill._1000, BankBill._100, BankBill._500, BankBill._50));
        Assertions.assertThat(atm.getBalance()).isEqualTo(1650);

        Collection<? extends BankBill> bankBills = atm.takeOut(1500);
        assertThat(bankBills, contains(BankBill._1000, BankBill._500));
        Assertions.assertThat(atm.getBalance()).isEqualTo(150);
    }

    @Test
    public void shouldThrowPickingException() {
        DefaultATM atm = new DefaultATM(cashBox, calculator);
        atm.add(List.of(BankBill._1000, BankBill._100, BankBill._500, BankBill._50));
        org.junit.jupiter.api.Assertions.assertThrows(TakingOutException.class, () ->
                atm.takeOut(1250));
        org.junit.jupiter.api.Assertions.assertThrows(TakingOutException.class, () ->
                atm.takeOut(1700));
    }

}