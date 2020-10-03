package ru.otus.core.dao.impls;

import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.jdbc.mapper.JdbcMapper;

public class UserDaoJdbcMapper extends AbstractDaoJdbcMapper<User, Long> implements UserDao {

    public UserDaoJdbcMapper(JdbcMapper<User> jdbcMapper) {
        super(jdbcMapper);
    }

    @Override
    protected Long getId(User entity) {
        return entity.getId();
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
