package ru.otus.servlet;

import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.model.User;
import ru.otus.core.service.UserDBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


public class UsersApiServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UsersApiServlet.class);

    private static final int ID_PATH_PARAM_POSITION = 1;

    private final UserDBService userDao;

    private final Gson gson;

    public UsersApiServlet(UserDBService userDao, Gson gson) {
        this.userDao = userDao;
        this.gson = gson;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String[] patches = splitPatches(request);
        if (isAllUsers(patches)) {
            List<? extends User> all = userDao.getAll();
            String json = gson.toJson(all);
            writeJsonResponse(json, Collections.emptyMap(), HttpStatus.Code.OK, response);
        } else {
            long id = extractIdFromRequest(patches);
            Optional<User> model = userDao.getModel(id);
            if (model.isPresent()) {
                String json = gson.toJson(model.get());
                writeJsonResponse(json, Collections.emptyMap(), HttpStatus.Code.OK, response);
            } else {
                writeJsonResponse(null, Collections.emptyMap(), HttpStatus.Code.NOT_FOUND, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User.Dto userDto = gson.fromJson(req.getReader(), User.Dto.class);
        if (userDto == null) {
            writeJsonResponse(null, Collections.emptyMap(), HttpStatus.Code.BAD_REQUEST, resp);
            return;
        }
        try {
            User user = User.fromDto(userDto);
            Long id = userDao.save(user);
            Optional<User> model = userDao.getModel(id);
            if (model.isPresent()) {
                String json = gson.toJson(model.get());
                writeJsonResponse(json, Collections.emptyMap(), HttpStatus.Code.CREATED, resp);
            } else {
                throw new RuntimeException("The model wasn't saved");
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            writeJsonResponse(null, Collections.emptyMap(), HttpStatus.Code.INTERNAL_SERVER_ERROR, resp);
        }
    }

    private void writeJsonResponse(String json, Map<String, String> additionalHeaders, HttpStatus.Code status, HttpServletResponse response) throws IOException {
        response.addHeader("Content-type", "application/json");
        additionalHeaders.forEach(response::addHeader);
        response.setStatus(status.getCode());
        if (Objects.nonNull(json)) {
            response.getWriter().write(json);
        }
    }

    private String[] splitPatches(HttpServletRequest request) {
        return request.getPathInfo().split("/");
    }

    private boolean isAllUsers(String[] patches) {
        return patches.length == 1;
    }

    private long extractIdFromRequest(String[] patches) {
        String id = (patches.length > 1)? patches[ID_PATH_PARAM_POSITION] : String.valueOf(-1);
        return Long.parseLong(id);
    }

}
