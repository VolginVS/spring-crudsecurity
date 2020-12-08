package ru.volginvs.crudsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.volginvs.crudsecurity.model.Role;
import ru.volginvs.crudsecurity.model.User;
import ru.volginvs.crudsecurity.service.RoleService;
import ru.volginvs.crudsecurity.service.UserService;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
   // http://localhost:8080/spring_crudsecurity_war_exploded/
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping(value = {"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";
    }

    @GetMapping(value = "/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute("userForm") User userForm) {
        roleService.addRoleToUserByRoleName(userForm,"ROLE_USER");
        userService.save(userForm);
        return "redirect:/login";
    }

    @GetMapping(value = "user")
    public String getUserInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username= auth.getName();

        User user = userService.getByUsername(username);
        model.addAttribute("user", user);
        return "user-info";
    }

    @GetMapping(value = "admin")
    public String getAdminCrudTool(Model model, User user) {
        List<User> userList = userService.getUserList();
        model.addAttribute("userList", userList);
        return "users";
    }

    @PostMapping(value = "user-create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "user-edit/{id}")
    public String getUserEditPage(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        Set<Role> roleSet = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("roleSet", roleSet);
        return "user-edit";
    }

    @PostMapping(value = "user-edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeById(id);
        return "redirect:/admin";
    }
}
