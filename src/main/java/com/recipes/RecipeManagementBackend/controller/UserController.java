package com.recipes.RecipeManagementBackend.controller;

import com.recipes.RecipeManagementBackend.model.User;
import com.recipes.RecipeManagementBackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("/api/rest/User.svc")
public class UserController {
    private static Logger LOG
            = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        LOG.info("getUserById: " + id);
        User user = userService.getUserById(id);
        return user;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        LOG.info("getAllUsers");
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody User user) {
        LOG.info("saveUser: " + user);
        userService.saveUser(user);
    }
}
