package com.recipes.RecipeManagementBackend.repository;

import com.recipes.RecipeManagementBackend.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
