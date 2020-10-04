package ru.otus.jdbc.mapper.impls;

import ru.otus.core.model.Account;
import ru.otus.jdbc.mapper.Adapter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountAdapter implements Adapter<Account> {

    @Override
    public Account convert(ResultSet rs) throws SQLException {
        return new Account(rs.getLong("no"), rs.getString("type"), rs.getDouble("rest"));
    }

    @Override
    public Object extractId(ResultSet rs) throws SQLException {
        return rs.getLong(1);
    }
}
