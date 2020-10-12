package ru.otus.hibernate;

import org.hibernate.SessionFactory;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.core.sessionmanager.SessionManagerException;

public class HibernateSessionManager implements SessionManager {

    private final SessionFactory sessionFactory;

    private HibernateDatabaseSession hibernateDatabaseSession;

    public HibernateSessionManager(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new IllegalArgumentException("session factory is null");
        }
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void beginSession() {
        try {
            hibernateDatabaseSession = new HibernateDatabaseSession(sessionFactory.openSession());
        } catch (Exception e) {
            throw new SessionManagerException(e);
        }
    }

    @Override
    public void commitSession() {
        checkSession();
        try {
            hibernateDatabaseSession.getTransaction().commit();
        } catch (Exception e) {
            throw new SessionManagerException(e);
        }
    }

    @Override
    public void rollbackSession() {
        checkSession();
        try {
            hibernateDatabaseSession.getTransaction().rollback();
        } catch (Exception e) {
            throw new SessionManagerException(e);
        }
    }

    @Override
    public void close() {
        if (hibernateDatabaseSession == null) {
            return;
        }

        var session = hibernateDatabaseSession.getSession();
        if (session == null || !session.isConnected()) {
            hibernateDatabaseSession = null;
            return;
        }

        hibernateDatabaseSession.close();
        hibernateDatabaseSession = null;
    }

    @Override
    public HibernateDatabaseSession getCurrentSession() {
        checkSession();
        return hibernateDatabaseSession;
    }
    
    private void checkSession() {
        if (hibernateDatabaseSession == null) {
            throw new SessionManagerException("the hibernate session is null");
        }
        
        var session = hibernateDatabaseSession.getSession();
        if (session == null || !session.isConnected()) {
            throw new SessionManagerException("the hibernate session is not opened");
        }

        var transaction = hibernateDatabaseSession.getTransaction();
        if (transaction == null || !transaction.isActive()) {
            throw new SessionManagerException("the transaction of hibernate session is not active");
        }
    }
    
}
