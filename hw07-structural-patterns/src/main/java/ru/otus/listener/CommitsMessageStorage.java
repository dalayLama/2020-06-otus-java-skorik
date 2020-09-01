package ru.otus.listener;

import java.util.Collection;

public interface CommitsMessageStorage {

    void save(CommitMessage commitMessage);

    Collection<CommitMessage> getAll();

}
