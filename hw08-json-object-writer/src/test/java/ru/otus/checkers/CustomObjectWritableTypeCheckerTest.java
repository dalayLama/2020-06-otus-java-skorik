package ru.otus.checkers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.WritableType;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class CustomObjectWritableTypeCheckerTest {

    @Test
    @DisplayName("Должен вернуть CUSTOM_OBJECT в методе getType")
    public void shouldReturnCUSTOM_OBJECTInMethodGetType() {
        assertThat(new CustomObjectWritableTypeChecker().getType()).isEqualTo(WritableType.CUSTOM_OBJECT);
    }

    @Test
    @DisplayName("Должен вернуть true для объекта не являещегося числом, строкой, символом, null-ом, коллекцией, массиов или boolean-ом")
    public void shouldReturnTrueForObjectIsNotNumberStringCharNullCollectionArrayBoolean() {
        CustomObjectWritableTypeChecker checker = new CustomObjectWritableTypeChecker();
        assertThat(checker.isKnowType(1)).isEqualTo(false);
        assertThat(checker.isKnowType("s")).isEqualTo(false);
        assertThat(checker.isKnowType('c')).isEqualTo(false);
        assertThat(checker.isKnowType(null)).isEqualTo(false);
        assertThat(checker.isKnowType(new ArrayList<>())).isEqualTo(false);
        assertThat(checker.isKnowType(new Object[1])).isEqualTo(false);
        assertThat(checker.isKnowType(true)).isEqualTo(false);
        assertThat(checker.isKnowType(new Object())).isEqualTo(true);
    }

}