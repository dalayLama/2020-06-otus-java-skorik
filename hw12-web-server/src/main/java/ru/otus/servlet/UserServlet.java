package ru.otus.servlet;

import ru.otus.server.ExtendedUserPrincipal;
import ru.otus.services.TemplateProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserServlet extends HttpServlet {

    private static final String USER_PAGE_TEMPLATE = "hello-user.html";

    private static final String TEMPLATE_ATTR_USER = "user";

    private final TemplateProcessor templateProcessor;

    public UserServlet(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExtendedUserPrincipal userPrincipal = (ExtendedUserPrincipal) req.getUserPrincipal();
        Map<String, Object> paramsMap = new HashMap<>();
        if (Objects.nonNull(userPrincipal)) {
            paramsMap.put(TEMPLATE_ATTR_USER, userPrincipal.getPublicUserData());
        }
        resp.setContentType("text/html");
        resp.getWriter().println(templateProcessor.getPage(USER_PAGE_TEMPLATE, paramsMap));
    }

}
