package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RolesService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.util.UsersValidator;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private final UserService userService;
    private final RolesService rolesService;
    private final UsersValidator usersValidator;
    @Autowired
    public AdminController(UserService userService, RolesService rolesService, UsersValidator usersValidator) {
        this.userService = userService;
        this.rolesService= rolesService;
        this.usersValidator = usersValidator;
    }


    @GetMapping()
    public String index(Model model, Principal principal) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("admin", userService.getPersonByUsername(principal.getName()));
        model.addAttribute("user", userService.getPersonByUsername(principal.getName()));
        model.addAttribute("allRoles", rolesService.getRoles());
        model.addAttribute("newUser", new User());
        return "admin/index";

    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}
