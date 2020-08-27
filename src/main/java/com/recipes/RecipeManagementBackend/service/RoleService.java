package com.recipes.RecipeManagementBackend.service;

import com.recipes.RecipeManagementBackend.model.Role;
import com.recipes.RecipeManagementBackend.model.Roles;
import com.recipes.RecipeManagementBackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Role findByRole(Roles role){
        return roleRepository.findByRole(role);
    }

}
