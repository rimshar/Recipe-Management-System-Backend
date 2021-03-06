package com.recipes.RecipeManagementBackend.model;

import javax.persistence.*;

@Entity
@Table(name = "v_ingredients_in_recipe")
public class RecipeIngredientView {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private Long ingredientCount;

    @Column
    private String username;

    @Column
    private String pictureLink;

    public String getPictureLink() {
        return pictureLink;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getIngredientCount() {
        return ingredientCount;
    }

    public String getUsername() {
        return username;
    }
}
