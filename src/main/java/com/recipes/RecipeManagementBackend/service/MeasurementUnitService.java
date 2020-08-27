package com.recipes.RecipeManagementBackend.service;

import com.recipes.RecipeManagementBackend.exception.EntityNotFoundException;
import com.recipes.RecipeManagementBackend.model.MeasurementUnit;
import com.recipes.RecipeManagementBackend.repository.MeasurementUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
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
        return measurementUnitRepository.findAll();
    }

    @Transactional
    public MeasurementUnit saveMeasurementUnit(MeasurementUnit measurementUnit){
        return measurementUnitRepository.save(measurementUnit);
    }

    public MeasurementUnit getMeasurementUnitByName(String name){
        return measurementUnitRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Measurement Unit " + name + " not found!"));
    }

}
