package ru.otus.core.dao.impls;

import ru.otus.core.dao.AccountDao;
import ru.otus.core.model.Account;
import ru.otus.jdbc.mapper.JdbcMapper;

public class AccountDaoJdbcMapper extends AbstractDaoJdbcMapper<Account, Long> implements AccountDao {

    public AccountDaoJdbcMapper(JdbcMapper<Account> jdbcMapper) {
        super(jdbcMapper);
    }

    @Override
    protected Long getId(Account entity) {
        return entity.getNo();
    }

    @Override
    protected Class<Account> getEntityClass() {
        return Account.class;
    }
}
