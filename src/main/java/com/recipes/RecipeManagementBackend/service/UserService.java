package com.recipes.RecipeManagementBackend.service;

import com.recipes.RecipeManagementBackend.exception.EntityNotFoundException;
import com.recipes.RecipeManagementBackend.model.Role;
import com.recipes.RecipeManagementBackend.model.Roles;
import com.recipes.RecipeManagementBackend.model.User;
import com.recipes.RecipeManagementBackend.model.UserTO;
import com.recipes.RecipeManagementBackend.repository.RoleRepository;
import com.recipes.RecipeManagementBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
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

    @Transactional
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User " + username + " not found!"));
    }

    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public Long getUserRoleByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User " + username + " not found!"));
        return user.getRole().getId();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public User save(final UserTO user) {

        if (usernameExists(user.getUsername())) {
            throw new EntityNotFoundException("User " + user.getUsername() + " already exists");
        }

        final User userEntity = new User();
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setRole(roleRepository.findByRole(user.getRole()));
        return userRepository.save(userEntity);
    }

    @Transactional
    public void changeRole(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User " + id + " not found!"));
        Role adminRole = roleRepository.findByRole(Roles.ROLE_ADMIN);
        Role userRole = roleRepository.findByRole(Roles.ROLE_USER);

        if (user.getRole() == userRole) {
            user.setRole(adminRole);
        } else if (user.getRole() == adminRole) {
            user.setRole(userRole);
        }
        userRepository.save(user);

    }
}
