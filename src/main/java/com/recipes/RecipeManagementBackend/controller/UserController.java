package com.recipes.RecipeManagementBackend.controller;

import com.recipes.RecipeManagementBackend.model.Recipe;
import com.recipes.RecipeManagementBackend.model.User;
import com.recipes.RecipeManagementBackend.model.UserTO;
import com.recipes.RecipeManagementBackend.service.RecipeService;
import com.recipes.RecipeManagementBackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("/api/rest/User.svc")
public class UserController {
    private static Logger LOG
            = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private RecipeService recipeService;

    @Autowired
    public UserController(UserService userService, RecipeService recipeService) {
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        LOG.info("getUserById: " + id);
        return userService.getUserById(id);
    }

    @GetMapping("/{username}/recipes")
    public List<Recipe> getAllRecipesByUsername(@PathVariable String username) {
        LOG.info("getAllRecipesByUsername: " + username);
        return recipeService.getAllRecipesByUsername(username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        LOG.info("getAllUsers");
        return userService.getAllUsers();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/user")
    public void changeRole(@RequestBody Long id) {
        LOG.info("Request received for " + id);
        userService.changeRole(id);
    }

    @PostMapping("/register")
    public void saveUser(@RequestBody UserTO user) {
        LOG.info("saveUser: " + user.toString());
        userService.save(user);
    }
}
