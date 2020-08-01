package com.recipes.RecipeManagementBackend.service;

import com.recipes.RecipeManagementBackend.exception.EntityNotFoundException;
import com.recipes.RecipeManagementBackend.model.User;
import com.recipes.RecipeManagementBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User " + id + " not found!"));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User " + username + " not found!"));
    }

    public List<User> getAllUsers() {
        Iterable<User> iterable
                = userRepository.findAll();
        List<User> result
                = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }


}
