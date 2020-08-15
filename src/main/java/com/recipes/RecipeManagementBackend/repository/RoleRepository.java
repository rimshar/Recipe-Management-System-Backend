package com.recipes.RecipeManagementBackend.repository;

import com.recipes.RecipeManagementBackend.model.Role;
import com.recipes.RecipeManagementBackend.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(Roles role);
}
