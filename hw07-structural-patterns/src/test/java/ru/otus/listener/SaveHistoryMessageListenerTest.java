package ru.otus.listener;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.Message;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaveHistoryMessageListenerTest {

    private static final Message ANY_MESSAGE1 = new Message.Builder().field1("hello").build();

    private static final Message ANY_MESSAGE2 = new Message.Builder().field1("world").build();

    @Test
    @DisplayName("Должен вызвать метод save один раз")
    void shouldCallMethodSaveOnlyOneTime() {
        CommitsMessageStorage storage = mock(CommitsMessageStorage.class);
        SaveHistoryMessageListener listener = new SaveHistoryMessageListener(storage);
        listener.onUpdated(ANY_MESSAGE1, ANY_MESSAGE2);
        verify(storage, times(1)).save(any());
    }

}