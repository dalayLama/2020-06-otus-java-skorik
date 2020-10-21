package ru.otus.servlet;

import ru.otus.core.model.User;
import ru.otus.core.service.UserDBService;
import ru.otus.server.ExtendedUserPrincipal;
import ru.otus.server.Roles;
import ru.otus.services.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class UsersServlet extends HttpServlet {

    private static final String USER_HELLO_PAGE_TEMPLATE = "user-hello.html";

    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";

    private static final String TEMPLATE_ATTR_USER = "user";

    private static final String TEMPLATE_ATTR_LIST_USERS = "list_users";

    private final UserDBService userDao;

    private final TemplateProcessor templateProcessor;

    public UsersServlet(TemplateProcessor templateProcessor, UserDBService userDao) {
        this.templateProcessor = templateProcessor;
        this.userDao = userDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        ExtendedUserPrincipal userPrincipal = (ExtendedUserPrincipal) req.getUserPrincipal();
        Map<String, Object> paramsMap = new HashMap<>();
        if (Objects.nonNull(userPrincipal)) {
            paramsMap.put(TEMPLATE_ATTR_USER, userPrincipal.getpublicUData());
        }
        if (req.isUserInRole(Roles.ADMIN.name())) {
            helloAdmin(paramsMap, response);
        } else {
            helloUser(paramsMap, response);
        }
    }

    protected void helloUser(Map<String, Object> paramsMap, HttpServletResponse response) throws IOException {
        printPage(response, USER_HELLO_PAGE_TEMPLATE, paramsMap);
    }

    private void helloAdmin(Map<String, Object> paramsMap, HttpServletResponse response) throws IOException {
        List<? extends User> userList = userDao.getAll();
        paramsMap.put(TEMPLATE_ATTR_LIST_USERS, userList);
        printPage(response, ADMIN_PAGE_TEMPLATE, paramsMap);
    }

    protected void printPage(HttpServletResponse response, String template, Map<String, Object> paramsMap) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(template, paramsMap));
    }

}
