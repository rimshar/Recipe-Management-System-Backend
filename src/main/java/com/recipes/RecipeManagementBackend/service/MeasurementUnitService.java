package com.recipes.RecipeManagementBackend.service;

import com.recipes.RecipeManagementBackend.exception.EntityNotFoundException;
import com.recipes.RecipeManagementBackend.model.MeasurementUnit;
import com.recipes.RecipeManagementBackend.repository.MeasurementUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MeasurementUnitService {

    private MeasurementUnitRepository measurementUnitRepository;

    @Autowired
    public MeasurementUnitService(MeasurementUnitRepository measurementUnitRepository){
        this.measurementUnitRepository = measurementUnitRepository;
    }

    public MeasurementUnit getMeasurementUnitById(Long id) {
        return measurementUnitRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Measurement Unit " + id + " not found!"));
    }

    public List<MeasurementUnit> getAllMeasurementUnits() {
        Iterable<MeasurementUnit> iterable
                = measurementUnitRepository.findAll();
        List<MeasurementUnit> result
                = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }

    public MeasurementUnit saveMeasurementUnit(MeasurementUnit measurementUnit){
        return measurementUnitRepository.save(measurementUnit);
    }

}
