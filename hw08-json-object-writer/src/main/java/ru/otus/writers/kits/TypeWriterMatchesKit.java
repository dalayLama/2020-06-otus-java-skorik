package ru.otus.writers.kits;

import ru.otus.JsonWriter;
import ru.otus.types.Type;

import java.util.Map;

public interface TypeWriterMatchesKit {

    Map<Type, JsonWriter> getMatches();

}
