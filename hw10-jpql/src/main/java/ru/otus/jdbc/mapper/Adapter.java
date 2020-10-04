package ru.otus.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Adapter<T> {

    T convert(ResultSet rs) throws SQLException;

    Object extractId(ResultSet rs) throws SQLException;

}
