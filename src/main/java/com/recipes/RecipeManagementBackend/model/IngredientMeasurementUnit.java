package com.recipes.RecipeManagementBackend.model;

import javax.persistence.*;

//Defines allowed Measurement Units for specific Ingredients

@Entity
public class IngredientMeasurementUnit {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long ingredientId;

    @OneToOne
    @JoinColumn(name = "ingredient")
    private Ingredient ingredient;

    @Column
    private Long measurementUnitId;

    @OneToOne
    @JoinColumn(name = "measurement_unit")
    private MeasurementUnit measurementUnit;

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
}
