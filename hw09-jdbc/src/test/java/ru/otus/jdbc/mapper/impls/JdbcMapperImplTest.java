package ru.otus.jdbc.mapper.impls;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.HomeWork;
import ru.otus.core.model.User;
import ru.otus.h2.DataSourceH2;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.DbExecutorImpl;
import ru.otus.jdbc.mapper.EntityClassMetaData;
import ru.otus.jdbc.mapper.EntitySQLMetaData;
import ru.otus.jdbc.mapper.JdbcMapper;
import ru.otus.jdbc.mapper.exceptions.NotNullIdException;
import ru.otus.jdbc.mapper.exceptions.NullIdException;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JdbcMapperImplTest {

    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);

    private JdbcMapper<User> jdbcMapper;

    private SessionManagerJdbc sm;

    @BeforeEach
    public void setUp() throws SQLException {
        DataSource dataSource = new DataSourceH2();
        flywayMigrations(dataSource);

        sm = new SessionManagerJdbc(dataSource);
        DbExecutor dbExecutor = new DbExecutorImpl();
        EntityClassMetaData<User> meta = new EntityClassMetaDataByReflection<>(User.class);
        EntitySQLMetaData sqlMetaData = new EntitySQLMetaDataByClassMeta(meta);

        jdbcMapper = new JdbcMapperImpl<>(sm, dbExecutor, sqlMetaData, meta);
    }

    @Test
    public void shouldSelectById() {
        String expectedName = "Artur";
        int expectedAge = 27;

        sm.beginSession();
        User byId = jdbcMapper.findById(1L, User.class);
        sm.commitSession();
        sm.close();

        assertThat(byId.getName()).isEqualTo(expectedName);
        assertThat(byId.getAge()).isEqualTo(expectedAge);
    }

    @Test
    public void shouldReturnNull() {
        sm.beginSession();
        User byId = jdbcMapper.findById(99L, User.class);
        sm.commitSession();
        sm.close();

        assertThat(byId).isNull();
    }

    @Test
    public void shouldInsertWithoutErrors() {
        User newUser = new User(null, "Somebody", 67);
        sm.beginSession();
        jdbcMapper.insert(newUser);
        sm.commitSession();
        assertThat(newUser.getId()).isNotNull();
        sm.close();
    }

    @Test
    public void shouldUpdateWithoutErrors() {
        User newValues = new User(1L, "Not artur", 78);
        sm.beginSession();
        jdbcMapper.update(newValues);
        sm.commitSession();
        User byId = jdbcMapper.findById(1L, User.class);
        assertThat(byId.getId()).isEqualTo(newValues.getId());
        assertThat(byId.getName()).isEqualTo(newValues.getName());
        assertThat(byId.getAge()).isEqualTo(newValues.getAge());
        sm.close();
    }

    @Test
    public void shouldThrowNotNullIdException() {
        User newUser = new User(10L, "new user", 22);
        sm.beginSession();
        NotNullIdException exception = assertThrows(NotNullIdException.class, () -> {
            jdbcMapper.insert(newUser);
        });
        sm.close();
    }

    @Test
    public void shouldThrowNullIdException() {
        User newUser = new User(null, "old user", 22);
        sm.beginSession();
        NullIdException exception = assertThrows(NullIdException.class, () -> {
            jdbcMapper.update(newUser);
        });
        sm.close();
    }

    private static void flywayMigrations(DataSource dataSource) {
        logger.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        logger.info("db migration finished.");
        logger.info("***");
    }

}