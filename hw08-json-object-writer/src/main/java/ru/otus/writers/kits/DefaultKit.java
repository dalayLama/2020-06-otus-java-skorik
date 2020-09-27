package ru.otus.writers.kits;

import ru.otus.JsonWriter;
import ru.otus.WriterDefiner;
import ru.otus.types.Type;
import ru.otus.writers.*;

import java.util.Map;

public class DefaultKit implements TypeWriterMatchesKit {

    private final WriterDefiner writerDefiner;

    private final ReaderFields readerFields;

    public DefaultKit(WriterDefiner writerDefiner, ReaderFields readerFields) {
        this.writerDefiner = writerDefiner;
        this.readerFields = readerFields;
    }

    @Override
    public Map<Type, JsonWriter> getMatches() {
        return Map.of(
                Type.ARRAY, new ArrayJsonWriter(writerDefiner),
                Type.ITERABLE, new IterableJsonWriter(writerDefiner),
                Type.NUMBER, new SimpleJsonWriter(),
                Type.STRING, new StringJsonWriter(),
                Type.CHAR, new StringJsonWriter(),
                Type.BOOLEAN, new SimpleJsonWriter(),
                Type.CUSTOM_OBJECT, new CustomObjectJsonWriter(writerDefiner, readerFields),
                Type.NULL, new SimpleJsonWriter()
        );
    }
}
