package ru.otus.controllers;

import org.springframework.ui.Model;
import ru.otus.core.service.UserDBService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UsersController {

    private final UserDBService userDBService;

    public UsersController(UserDBService userDBService) {
        this.userDBService = userDBService;
    }

    @GetMapping({"/", "/users"})
    public String usersView(Model model) {
        model.addAttribute("users", userDBService.getAll());
        return "users";
    }

}
