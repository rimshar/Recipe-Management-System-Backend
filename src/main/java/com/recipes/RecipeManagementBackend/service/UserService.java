package com.recipes.RecipeManagementBackend.service;

import com.recipes.RecipeManagementBackend.exception.EntityNotFoundException;
import com.recipes.RecipeManagementBackend.model.User;
import com.recipes.RecipeManagementBackend.model.UserTO;
import com.recipes.RecipeManagementBackend.repository.RoleRepository;
import com.recipes.RecipeManagementBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User " + id + " not found!"));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User " + username + " not found!"));
    }
    public boolean usernameExists(String username){
        return userRepository.findByUsername(username).isPresent();
    }


    public Long getUserRoleByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User " + username + " not found!"));
        Long roleId = user.getRole().getId();
        return roleId;
    }


    public List<User> getAllUsers() {
        Iterable<User> iterable
                = userRepository.findAll();
        List<User> result
                = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public User save(final UserTO user) {

        if(usernameExists(user.getUsername())){
            throw new EntityNotFoundException("User"+ user.getUsername() + "user already exists");
        }

        final User userEntity = new User();
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setRole(roleRepository.findByRole(user.getRole()));
        return userRepository.save(userEntity);
    }
}
