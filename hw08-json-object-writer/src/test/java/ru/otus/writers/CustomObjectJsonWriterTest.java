package ru.otus.writers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.JsonWriterFactoryImpl;
import ru.otus.WriterDefiner;
import ru.otus.WriterDefinerByType;
import ru.otus.types.DefaultFactoryDefinerType;
import ru.otus.types.TypeDefiner;
import ru.otus.writers.kits.DefaultKit;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomObjectJsonWriterTest {

    @Test
    @DisplayName("Должен правильно сформировать json")
    public void shouldCreateCorrectJson() {
        ReflectionReaderFields reader = new ReflectionReaderFields();
        JsonWriterFactoryImpl writerFactory = new JsonWriterFactoryImpl();
        TypeDefiner definerType = new DefaultFactoryDefinerType().createDefinerType();
        WriterDefiner definer = new WriterDefinerByType(definerType, writerFactory);
        writerFactory.addWriters(new DefaultKit(definer, reader).getMatches());
        String expectedJson = "{testField:3,testField2:[1, 2, 3],strField:\"fff\",testField3:[{charField:\"f\"}, {charField:\"f\"}]}";

        CustomObjectJsonWriter jsonWriter = new CustomObjectJsonWriter(definer, reader);
        String result = jsonWriter.toJson(new TestClass());

        assertThat(result).isEqualTo(expectedJson);
        System.out.println(result);
    }

    private class TestClass {

        private int testField = 3;

        private int[] testField2 = {1, 2, 3};

        private String strField = "fff";

        private List<OtherTestClass> testField3 = List.of(
                new OtherTestClass(), new OtherTestClass()
        );

    }

    private class OtherTestClass {
        private char charField = 'f';
    }

}