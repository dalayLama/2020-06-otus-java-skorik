package ru.otus.listener;

import ru.otus.Message;

public class SaveHistoryMessageListener implements Listener {

    private final CommitsMessageStorage storage;

    public SaveHistoryMessageListener(CommitsMessageStorage storage) {
        this.storage = storage;
    }

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        storage.save(new CommitMessage(oldMsg, newMsg));
    }
}
