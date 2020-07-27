package com.recipes.RecipeManagementBackend.model;

import javax.persistence.*;

@Entity
public class MeasurementUnit {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
}
