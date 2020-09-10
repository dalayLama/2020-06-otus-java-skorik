package ru.otus.types.checkers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.types.WritableType;

import static org.assertj.core.api.Assertions.assertThat;

class CharWritableTypeCheckerTest {

    @DisplayName("Должен вернуть true для char")
    @Test
    public void shouldReturnTrueForChar() {
        CharWritableTypeChecker checker = new CharWritableTypeChecker();
        assertThat(checker.isKnowType('a')).isEqualTo(true);
        assertThat(checker.getType()).isEqualTo(WritableType.CHAR);
    }

    @DisplayName("Должен вернуть false для не char")
    @Test
    public void shouldReturnFalseForNotChar() {
        CharWritableTypeChecker checker = new CharWritableTypeChecker();
        assertThat(checker.isKnowType("s")).isEqualTo(false);
    }

    @DisplayName("В методе getType должен возвращать CHAR")
    @Test
    public void shouldReturnCharInMethodGetType() {
        assertThat(new CharWritableTypeChecker().getType()).isEqualTo(WritableType.CHAR);
    }

}