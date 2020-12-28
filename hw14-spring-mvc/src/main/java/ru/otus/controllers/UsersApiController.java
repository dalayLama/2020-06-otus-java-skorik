package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.core.model.User;
import ru.otus.core.service.UserDBService;

import javax.servlet.http.HttpServlet;
import java.util.Collection;
import java.util.Optional;


@RestController
public class UsersApiController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UsersApiController.class);

    private final UserDBService userDao;

    public UsersApiController(UserDBService userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/api/users")
    public Collection<? extends User> getUsers() {
        return userDao.getAll();
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        Optional<User> model = userDao.getModel(id);
        return model
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> saveUser(@RequestBody User.Dto dto) {
        try {
            User user = User.fromDto(dto);
            Long id = userDao.save(user);
            Optional<User> model = userDao.getModel(id);
            return model
                    .map(u -> new ResponseEntity<>(u, HttpStatus.CREATED))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
