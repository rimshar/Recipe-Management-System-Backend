package com.recipes.RecipeManagementBackend.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Ingridient_id")
    private Ingredient ingredient;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Measurement_unit_id")
    private MeasurementUnit measurementUnit;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Recipe_id")
    private Recipe recipe;
    @Column
    private Double quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
