package ru.otus.types.checkers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.types.WritableType;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class IterableWritableTypeCheckerTest {

    @Test
    @DisplayName("Должен вернуть true для Iterable")
    public void shouldReturnTrueForIterable() {
        List<Object> list = Stream.of(new Object()).collect(Collectors.toList());
        IterableWritableTypeChecker checker = new IterableWritableTypeChecker();
        assertThat(checker.isKnowType(list)).isEqualTo(true);
    }

    @Test
    @DisplayName("Должен вернуть false для массива")
    public void shouldReturnTrueForArray() {
        IterableWritableTypeChecker checker = new IterableWritableTypeChecker();
        assertThat(checker.isKnowType(new byte[1])).isEqualTo(false);
    }

    @Test
    @DisplayName("Должен вернуть false для null")
    public void shouldReturnTrueForNull() {
        IterableWritableTypeChecker checker = new IterableWritableTypeChecker();
        assertThat(checker.isKnowType(null)).isEqualTo(false);
    }

    @Test
    @DisplayName("Должен вернуть ITERABLE в методе getType")
    public void shouldReturnITERABLEInMethodGetType() {
        assertThat(new IterableWritableTypeChecker().getType()).isEqualTo(WritableType.ITERABLE);
    }

}