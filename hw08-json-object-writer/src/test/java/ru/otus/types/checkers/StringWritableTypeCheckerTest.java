package ru.otus.types.checkers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.types.WritableType;

import static org.assertj.core.api.Assertions.assertThat;

class StringWritableTypeCheckerTest {

    @Test
    @DisplayName("Должен вернуть true для String")
    public void shouldReturnTrueForString() {
        assertThat(new StringWritableTypeChecker().isKnowType("s")).isEqualTo(true);
    }

    @Test
    @DisplayName("Должен вернуть false для не String")
    public void shouldReturnFalseForNotString() {
        assertThat(new StringWritableTypeChecker().isKnowType('c')).isEqualTo(false);
    }

    @Test
    @DisplayName("Должен вернуть STRING в методе getType")
    public void shouldReturnSTRINGInMethodGetType() {
        assertThat(new StringWritableTypeChecker().getType()).isEqualTo(WritableType.STRING);
    }

}