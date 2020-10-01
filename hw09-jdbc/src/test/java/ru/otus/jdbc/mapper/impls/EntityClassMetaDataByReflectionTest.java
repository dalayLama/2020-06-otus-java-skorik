package ru.otus.jdbc.mapper.impls;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.core.annotations.Id;
import ru.otus.jdbc.mapper.exceptions.ReadEntityException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EntityClassMetaDataByReflectionTest {

    @Test
    @DisplayName("Должен правильно собрать метаинформацию")
    public void shouldCreateTakeMetaInformation() {
        EntityClassMetaDataByReflection<CorrectEntityClass> meta = new EntityClassMetaDataByReflection<>(CorrectEntityClass.class);

        assertThat(meta.getConstructor()).isNotNull();
        assertThat(meta.getIdField().getName()).isEqualTo("id");
        assertThat(meta.getIdField().isAnnotationPresent(Id.class)).isEqualTo(true);
        assertThat(meta.getAllFields().size()).isEqualTo(2);
        assertThat(meta.getFieldsWithoutId().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Должен выбросить исключение, что отсутствует конструктор по умолчанию")
    public void shouldThrowExceptionThatDefaultConstructorWasNotFound() {
        String expectedMessage = "default constructor not found";

        ReadEntityException exception = assertThrows(ReadEntityException.class, () -> {
            new EntityClassMetaDataByReflection<>(EntityClassWithoutDefaultConstruct.class);
        });
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    @DisplayName("Должен выбросить исключение, что идентификатор дублируется")
    public void shouldThrowExceptionThatIdDuplicate() {
        String expectedMessage = "duplicate id field";

        ReadEntityException exception = assertThrows(ReadEntityException.class, () -> {
            new EntityClassMetaDataByReflection<>(EntityClassDoubleId.class);
        });
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    @DisplayName("Должен выбросить исключение, что идентификатор отсутствует")
    public void shouldThrowExceptionThatIdNotFound() {
        String expectedMessage = "id field not found";

        ReadEntityException exception = assertThrows(ReadEntityException.class, () -> {
            new EntityClassMetaDataByReflection<>(EntityClassWithoutId.class);
        });
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    private static class CorrectEntityClass {

        @Id
        private long id;

        private String name;

        public CorrectEntityClass() {
        }
    }

    private static class EntityClassWithoutDefaultConstruct {}

    private static class EntityClassWithoutId {

        public EntityClassWithoutId() {
        }

        private Integer id;

    }

    private static class EntityClassDoubleId {

        public EntityClassDoubleId() {
        }

        @Id
        private Integer id;

        @Id
        private Integer id2;

    }

}