package com.mycompany.manageusertool.controller;

import com.mycompany.manageusertool.model.User;
import com.mycompany.manageusertool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("pageTitle","Add New User");
        return "user_form";
    }
    //Submition for user-form
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes radientAttributes) {
        userService.save(user);
        radientAttributes.addFlashAttribute("message", "User saved successfully");
        return "redirect:/users";
    }
    //for updating user
    @GetMapping("/users/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Integer id, Model model,RedirectAttributes radientAttributes) {
        try {
            User user = userService.getById(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle","Edit User (ID: " + id + ")");
            return "user_form";
        }catch (RuntimeException e) {
            radientAttributes.addFlashAttribute("message", "User Edited successfully");
        }
        return "redirect:/users";
    }
    //deleting user
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes radientAttributes) {
        try {
            userService.deleteById(id);
            radientAttributes.addFlashAttribute("message", "User Deleted successfully");
        }catch (RuntimeException e) {
            radientAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }
}
