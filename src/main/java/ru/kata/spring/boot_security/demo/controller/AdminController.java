package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;


    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping
    public String getAllUser(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("users", userService.listUsers());
        return "admin";
    }


    @PostMapping()
    public String saveNewUser(@ModelAttribute("user") User user, String role) {
        userService.addUser(user);
        userService.addRoleToUser(role, user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") long id, String role) {
        userService.updateUser(id, user);
        userService.addRoleToUser(role, user);
        return "redirect:/admin";
    }


    @PostMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}


