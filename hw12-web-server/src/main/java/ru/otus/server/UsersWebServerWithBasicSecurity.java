package ru.otus.server;

import com.google.gson.Gson;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.security.Constraint;
import ru.otus.core.service.UserDBService;
import ru.otus.services.TemplateProcessor;

import java.util.*;

public class UsersWebServerWithBasicSecurity extends UsersWebServerSimple {

    private static final String CONSTRAINT_NAME = "auth";

    private final Set<String> adminPatches = new HashSet<>();

    private final LoginService loginService;

    public UsersWebServerWithBasicSecurity(int port,
                                           LoginService loginService,
                                           UserDBService userDao,
                                           Gson gson,
                                           TemplateProcessor templateProcessor) {
        super(port, userDao, gson, templateProcessor);
        this.loginService = loginService;
    }

    public UsersWebServerWithBasicSecurity(int port,
                                           LoginService loginService,
                                           UserDBService userDao,
                                           Gson gson,
                                           TemplateProcessor templateProcessor,
                                           Collection<String> adminPatches) {
        super(port, userDao, gson, templateProcessor);
        this.loginService = loginService;
        this.adminPatches.addAll(adminPatches);
    }

    protected Handler applySecurity(ServletContextHandler servletContextHandler, String... paths) {
        Constraint userConstraint = createUserConstraint();
        Constraint adminConstraint = createAdminConstraint();

        List<ConstraintMapping> constraintMappings = new ArrayList<>();
        Arrays.stream(paths).forEachOrdered(path -> {
            ConstraintMapping mapping = new ConstraintMapping();
            mapping.setPathSpec(path);
            if (adminPatches.contains(path)) {
                mapping.setConstraint(adminConstraint);
            } else {
                mapping.setConstraint(userConstraint);
            }

            constraintMappings.add(mapping);
        });

        ConstraintSecurityHandler security = new ConstraintSecurityHandler();
        security.setAuthenticator(new BasicAuthenticator());

        security.setLoginService(loginService);
        security.setConstraintMappings(constraintMappings);
        security.setHandler(new HandlerList(servletContextHandler));

        return security;

    }

    protected Constraint createUserConstraint() {
        Constraint constraint = createDefaultConstraint();
        constraint.setRoles(Roles.allNames());
        return constraint;
    }

    protected Constraint createAdminConstraint() {
        Constraint constraint = createDefaultConstraint();
        constraint.setRoles(new String[]{Roles.ADMIN.name()});
        return constraint;
    }

    protected Constraint createDefaultConstraint() {
        Constraint constraint = new Constraint();
        constraint.setName(CONSTRAINT_NAME);
        constraint.setAuthenticate(true);
        return constraint;
    }

}
