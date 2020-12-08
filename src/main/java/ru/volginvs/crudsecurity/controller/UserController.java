package ru.volginvs.crudsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.volginvs.crudsecurity.model.Role;
import ru.volginvs.crudsecurity.model.User;
import ru.volginvs.crudsecurity.service.RoleService;
import ru.volginvs.crudsecurity.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDetailsService userDetailsService;



//---------------------------------------------------------
    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {
        //User user = userService.getUserById(1L);
        //model.addAttribute("user", user);
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
    public String registration(@ModelAttribute("userForm") User userForm, Model model) {

        userService.addRoleToUserByRoleName(userForm,"ROLE_USER");
        userService.save(userForm);

        return "redirect:/welcome";
    }

    @GetMapping(value = {"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

//------------------------------------------------
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
    public String createUser(User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "user-edit/{id}")
    public String getUserEditPage(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        List<Role> userRoles = new ArrayList(user.getRoles());
        List<Role> roleSet = new ArrayList<>(roleService.getAllRoles());
        model.addAttribute("user", user);
        model.addAttribute("roleSet", roleSet);
        model.addAttribute("userRoles",userRoles);
        return "user-edit";
    }

//    @PostMapping(value = "user-edit-edit-admin-role")
//    public String editUserRole(User user, Model model) {
//        User userEdited = userService.editAdminRole(user);
//        Long id = userEdited.getId();
//        model.addAttribute("user", userEdited);
//        return "user-edit/{id}";
//    }

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
