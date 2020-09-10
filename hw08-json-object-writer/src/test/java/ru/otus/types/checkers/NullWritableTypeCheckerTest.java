package ru.otus.types.checkers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.WritableType;

import static org.assertj.core.api.Assertions.assertThat;

class NullWritableTypeCheckerTest {

    @Test
    @DisplayName("Должен вернуть true для параметра null")
    public void shouldReturnTrueForNull() {
        NullWritableTypeChecker checker = new NullWritableTypeChecker();
        assertThat(checker.isKnowType(null)).isEqualTo(true);
    }

    @Test
    @DisplayName("Должен вернуть false для параметра не null")
    public void shouldReturnFalseForNotNull() {
        NullWritableTypeChecker checker = new NullWritableTypeChecker();
        assertThat(checker.isKnowType(new Object())).isEqualTo(false);
    }

    @Test
    @DisplayName("Должен вернуть NULL в методе getType")
    public void shouldReturnNULLInMethodGetType() {
        assertThat(new NullWritableTypeChecker().getType()).isEqualTo(WritableType.NULL);
    }

}