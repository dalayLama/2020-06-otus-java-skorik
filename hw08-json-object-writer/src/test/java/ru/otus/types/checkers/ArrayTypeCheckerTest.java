package ru.otus.types.checkers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.types.Type;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayTypeCheckerTest {

    @Test
    @DisplayName("Должен вернуть true если параметром является массив")
    public void shouldReturnTrueForArray() {
        ArrayWritableTypeChecker checker = new ArrayWritableTypeChecker();
        boolean knowType = checker.isKnowType(new double[0]);
        assertThat(knowType).isEqualTo(true);
    }

    @Test
    @DisplayName("Должен вернуть false для если параметром является не массив")
    public void shouldReturnFalseForNotArray() {
        ArrayWritableTypeChecker checker = new ArrayWritableTypeChecker();
        boolean knowType = checker.isKnowType(new Object());
        assertThat(knowType).isEqualTo(false);
    }

    @Test
    @DisplayName("Должен вернуть false если параметром является null")
    public void shouldReturnFalseForNull() {
        ArrayWritableTypeChecker checker = new ArrayWritableTypeChecker();
        boolean knowType = checker.isKnowType(null);
        assertThat(knowType).isEqualTo(false);
    }

    @Test
    @DisplayName("Должен вернуть ARRAY в методе getType")
    public void shouldReturnARRAYInMethodGetType() {
        assertThat(new ArrayWritableTypeChecker().getType()).isEqualTo(Type.ARRAY);
    }

}