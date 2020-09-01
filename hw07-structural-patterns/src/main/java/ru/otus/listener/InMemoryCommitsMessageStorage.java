package ru.otus.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class InMemoryCommitsMessageStorage implements CommitsMessageStorage {

    private final List<CommitMessage> commitMessages = new ArrayList<>();

    @Override
    public void save(CommitMessage commitMessage) {
        commitMessages.add(commitMessage);
    }

    @Override
    public Collection<CommitMessage> getAll() {
        return Collections.unmodifiableList(commitMessages);
    }
}
