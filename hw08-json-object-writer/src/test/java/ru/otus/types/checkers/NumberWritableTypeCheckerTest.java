package ru.otus.types.checkers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.WritableType;

import static org.assertj.core.api.Assertions.assertThat;

class NumberWritableTypeCheckerTest {

    @Test
    @DisplayName("Должен вернуть true для всех основных чисел")
    public void shouldReturnTrueForMajorNumbers() {
        NumberWritableTypeChecker checker = new NumberWritableTypeChecker();
        assertThat(checker.isKnowType((byte) 1)).isEqualTo(true);
        assertThat(checker.isKnowType((short) 1)).isEqualTo(true);
        assertThat(checker.isKnowType( 1)).isEqualTo(true);
        assertThat(checker.isKnowType(1L)).isEqualTo(true);
        assertThat(checker.isKnowType(1.0f)).isEqualTo(true);
        assertThat(checker.isKnowType(1.0d)).isEqualTo(true);
    }

    @Test
    @DisplayName("Должен вернуть false для не чисел")
    public void shouldReturnFalseForNotNumbers() {
        NumberWritableTypeChecker checker = new NumberWritableTypeChecker();
        assertThat(checker.isKnowType(new Object())).isEqualTo(false);
    }

    @Test
    @DisplayName("Должен вернуть NUMBER в методе getType")
    public void shouldReturnNUMBERInMethodGetType() {
        assertThat(new NumberWritableTypeChecker().getType()).isEqualTo(WritableType.NUMBER);
    }

}