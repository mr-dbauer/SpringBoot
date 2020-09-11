package com.springBootBauerProject.SpringBoot.controllers;

import com.springBootBauerProject.SpringBoot.model.Role;
import com.springBootBauerProject.SpringBoot.model.User;
import com.springBootBauerProject.SpringBoot.service.RoleService;
import com.springBootBauerProject.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping(value = "users")
    public String getUsers(ModelMap model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping(value = "newUser")
    public String getUser() {
        return "addUser";
    }

    @PostMapping(value = "new")
    public String addNewUser(@RequestParam(value = "login") String login,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "email") String email,
                             @RequestParam("role") String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(roleService.findRoleByName(roles));
        }
        userService.save(new User(login, password, email, roleSet ));
        return "redirect:users";
    }

    @GetMapping("edit")
    public String editPage(@RequestParam("id") long id, ModelMap model)
    {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("userRoles", roleService.findAllRoles());
        return "editUser";
    }

    @PostMapping("editSave")
    public String editUser(Model model,
                           @RequestParam("id") Long id,
                           @RequestParam("login") String login,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email,
                           HttpServletRequest req) {
        String[] requestRole = req.getParameterValues("roles");
        Set<Role> roleSet = new HashSet<>();
        for (String roles : requestRole) {
            roleSet.add(roleService.findRoleByName(roles));
        }
        userService.save(new User(id, login, password, email, roleSet ));
        return "redirect:users";
    }

    @GetMapping("delete")
    public String deleteUser(@RequestParam(value = "id") String id) {
        Long userId = Long.parseLong(id);
        userService.deleteById(userId);
        return "redirect:users";
    }
}