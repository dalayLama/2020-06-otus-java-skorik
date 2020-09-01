package ru.otus.processor;

import ru.otus.Message;
import ru.otus.processor.exceptions.CheckerException;
import ru.otus.processor.exceptions.TimeToDieException;

import java.time.LocalDateTime;

public class OddTimeChecker implements MessageChecker {

    private int currentSecond = -1;

    @Override
    public boolean isOk(Message message) {
        int currentSecond = LocalDateTime.now().getSecond();
        if (currentSecond % 2 == 0) {
            this.currentSecond = currentSecond;
            return false;
        } else {
            this.currentSecond = -1;
        }
        return true;
    }

    @Override
    public void throwException() throws CheckerException {
        throw new TimeToDieException(currentSecond);
    }
}
