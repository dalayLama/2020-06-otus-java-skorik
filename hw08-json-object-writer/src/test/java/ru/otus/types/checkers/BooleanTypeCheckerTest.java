package ru.otus.types.checkers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.types.Type;

import static org.assertj.core.api.Assertions.assertThat;

class BooleanTypeCheckerTest {

    @Test
    @DisplayName("Должен вернуть BOOLEAN в методе getType")
    public void shouldReturnBOOLEANInMethodGetType() {
        assertThat(new BooleanWritableTypeChecker().getType()).isEqualTo(Type.BOOLEAN);
    }

    @Test
    @DisplayName("Должен вернуть true для boolean")
    public void shouldReturnTrueForBoolean() {
        BooleanWritableTypeChecker checker = new BooleanWritableTypeChecker();
        assertThat(checker.isKnowType(true)).isEqualTo(true);
        assertThat(checker.isKnowType(false)).isEqualTo(true);
    }

    @Test
    @DisplayName("Должен вернуть false для не boolean")
    public void shouldReturnFalseForNotBoolean() {
        assertThat(new BooleanWritableTypeChecker().isKnowType("s")).isEqualTo(false);
    }

}