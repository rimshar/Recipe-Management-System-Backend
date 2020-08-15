package com.recipes.RecipeManagementBackend.model;

public class RecipeIngredientTO {
    private String measurementUnit;
    private String ingredient;
    private double quantity;

    public double getQuantity() {
        return quantity;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
