package ru.otus.core.service;

import ru.otus.core.dao.AccountDao;
import ru.otus.core.model.Account;

public class AccountDaoDBService extends AbstractDaoDBService<Account, Long> implements AccountDBService {

    public AccountDaoDBService(AccountDao dao) {
        super(dao);
    }

}