package ru.otus.jdbc.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.annotations.Id;
import ru.otus.jdbc.mapper.impls.EntityClassMetaDataByReflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EntitySQLMetaDataByClassMetaTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(EntitySQLMetaDataByClassMetaTest.class);

    private EntityClassMetaData<TestClass> meta;

    private EntitySQLMetaDataByClassMeta sql;

    @BeforeEach
    public void setUp() {
        meta = new EntityClassMetaDataByReflection<>(TestClass.class);
        sql = new EntitySQLMetaDataByClassMeta(meta);
    }

    @Test
    @DisplayName("Должен вернуть правильный запрос на selectAll")
    public void shouldReturnCorrectQuerySelectAll() {
        String expectedSql = "select * from testclass";
        String result = sql.getSelectAllSql();
        LOG.info(result);
        assertThat(result).isEqualTo(expectedSql);
    }

    @Test
    @DisplayName("Должен вернуть правильный запрос на selectById")
    public void shouldReturnCorrectQuerySelectById() {
        String expectedSql = "select * from testclass where someFieldId = ?";
        String result = sql.getSelectByIdSql();
        LOG.info(result);
        assertThat(result).isEqualTo(expectedSql);
    }

    @Test
    @DisplayName("Должен вернуть правильный запрос на getInsertSql")
    public void shouldReturnCorrectQueryInsertSql() {
        String expectedSql = "insert into testclass (name, age) values (?, ?)";
        String result = sql.getInsertSql();
        LOG.info(result);
        assertThat(result).isEqualTo(expectedSql);
    }

    @Test
    @DisplayName("Должен вернуть правильный запрос на getUpdateSql")
    public void shouldReturnCorrectQueryUpdateSql() {
        String expectedSql = "update testclass set name = ?, age = ? where someFieldId = ?";
        String result = sql.getUpdateSql();
        LOG.info(result);
        assertThat(result).isEqualTo(expectedSql);
    }

    private static class TestClass {

        @Id
        private long someFieldId;

        private String name;

        private int age;

        public TestClass() {
        }

    }

}