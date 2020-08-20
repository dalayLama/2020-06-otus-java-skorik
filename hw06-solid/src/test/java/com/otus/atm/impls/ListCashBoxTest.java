package com.otus.atm.impls;

import com.otus.atm.BankBill;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

class ListCashBoxTest {

    @Test
    void shouldRemoveWhenTakeAway() {
        ListCashBox box = new ListCashBox();
        box.add(List.of(
                BankBill._100, BankBill._50, BankBill._1000
        ));
        Collection<? extends BankBill> taken = box.takeAway(List.of(BankBill._1000, BankBill._100));
        assertThat(taken, contains(BankBill._1000, BankBill._100));
        assertThat(box.getAll(), hasSize(1));
        assertThat(box.getAll(), contains(BankBill._50));
    }

}