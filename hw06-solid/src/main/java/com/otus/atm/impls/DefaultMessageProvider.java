package com.otus.atm.impls;

import com.otus.atm.MessageProvider;

public class DefaultMessageProvider implements MessageProvider {
    @Override
    public String getMessageShowBalance(long amount) {
        return String.format("Ваш баланс составляет: %d", amount);
    }
}
