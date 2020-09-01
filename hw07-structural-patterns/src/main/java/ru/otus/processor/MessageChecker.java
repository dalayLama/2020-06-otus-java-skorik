package ru.otus.processor;

import ru.otus.Message;
import ru.otus.processor.exceptions.CheckerException;

public interface MessageChecker {

    boolean isOk(Message message);

    void throwException() throws CheckerException;

}
