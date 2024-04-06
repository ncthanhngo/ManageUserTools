package com.mycompany.manageusertool.controller;

import com.mycompany.manageusertool.model.User;
import com.mycompany.manageusertool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public String showUsersList(Model model) {
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }
    @GetMapping("/users/new")
    public String showNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user_form";
    }
}
