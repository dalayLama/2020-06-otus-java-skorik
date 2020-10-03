package ru.otus.jdbc.mapper.impls;

import ru.otus.core.model.User;
import ru.otus.jdbc.mapper.Adapter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAdapter implements Adapter<User> {
    @Override
    public User convert(ResultSet rs) throws SQLException {
        return new User(rs.getLong("id"), rs.getString("name"), rs.getInt("age"));
    }

    @Override
    public Object extractId(ResultSet rs) throws SQLException {
        return rs.getLong(1);
    }
}
