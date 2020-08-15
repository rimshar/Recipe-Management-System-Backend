package com.recipes.RecipeManagementBackend.model;

import javax.persistence.*;

//Defines allowed Measurement Units for specific Ingredients

@Entity
@Table(name="ingredient_has_measurement_unit")
public class IngredientMeasurementUnit {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long ingredientId;

    @OneToOne
    @JoinColumn(name = "ingredientId", insertable = false, updatable = false)
    private Ingredient ingredient;

    @Column
    private Long measurementUnitId;

    @OneToOne
    @JoinColumn(name = "measurementUnitId", insertable = false, updatable = false)
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

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Long getMeasurementUnitId() {
        return measurementUnitId;
    }

    public void setMeasurementUnitId(Long measurementUnitId) {
        this.measurementUnitId = measurementUnitId;
    }
}
