package ru.otus.db;

import ru.otus.db.exceptions.MigrateException;

public interface MigrationManager {

    void migrate() throws MigrateException;

}
