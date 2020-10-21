package ru.otus.services;

import org.eclipse.jetty.security.AbstractLoginService;
import ru.otus.core.model.User;
import ru.otus.core.service.UserDBService;
import ru.otus.server.ExtendedUserPrincipal;
import ru.otus.server.Roles;

import java.util.Optional;

public class UserDBServiceLoginService extends AbstractLoginService {

    private final UserDBService userDao;

    public UserDBServiceLoginService(UserDBService userDao) {
        this.userDao = userDao;
    }

    @Override
    protected String[] loadRoleInfo(UserPrincipal userPrincipal) {
        return new String[] {Roles.ADMIN.name()};
    }

    @Override
    protected ExtendedUserPrincipal loadUserInfo(String login) {
        Optional<User> dbUser = userDao.findByLogin(login);
        return dbUser.map(ExtendedUserPrincipal::new).orElse(null);
    }
}
