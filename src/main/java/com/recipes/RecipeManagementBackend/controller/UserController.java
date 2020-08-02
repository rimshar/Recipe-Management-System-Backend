package com.recipes.RecipeManagementBackend.controller;

import com.recipes.RecipeManagementBackend.model.Recipe;
import com.recipes.RecipeManagementBackend.model.User;
import com.recipes.RecipeManagementBackend.model.UserTO;
import com.recipes.RecipeManagementBackend.service.RecipeService;
import com.recipes.RecipeManagementBackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("/api/rest/User.svc")
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        LOG.info("getUserById: " + id);
        User user = userService.getUserById(id);
        return user;
    }

    @GetMapping("/{username}/recipes")
    public List<Recipe> getAllRecipesByUsername(@PathVariable String username) {
        LOG.info("getAllRecipesByUsername: " + username);
        return recipeService.getAllRecipesByUsername(username);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        LOG.info("getAllUsers");
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public void saveUser(@RequestBody UserTO user) {
        LOG.info("saveUser: " + user);
        userService.save(user);
    }
}
