package ru.otus.listener;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.Message;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryCommitsMessageStorageTest {

    @Test
    @DisplayName("Должен сохранить все сообщения")
    public void shouldSaveAllMessages() {
        CommitsMessageStorage storage = new InMemoryCommitsMessageStorage();
        Message.Builder builder = new Message.Builder();
        List<CommitMessage> commits = List.of(
                new CommitMessage(builder.field11("783").field13("666").build(), builder.field11("aaa").field13("aaa").build()),
                new CommitMessage(builder.field11("111").field13("888").build(), builder.field11("bbb").field13("bbb").build()),
                new CommitMessage(builder.field11("907").field13("142").build(), builder.field11("ccc").field13("ccc").build()));
        commits.forEach(storage::save);

        Collection<CommitMessage> all = storage.getAll();
        assertThat(all).containsAll(commits);
    }

}