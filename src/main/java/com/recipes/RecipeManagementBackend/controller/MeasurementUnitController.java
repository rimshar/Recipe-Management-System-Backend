package com.recipes.RecipeManagementBackend.controller;

import com.recipes.RecipeManagementBackend.RecipeManagementBackendApplication;
import com.recipes.RecipeManagementBackend.model.MeasurementUnit;
import com.recipes.RecipeManagementBackend.service.MeasurementUnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rest/MeasurementUnit.svc")
public class MeasurementUnitController {

    private static Logger LOG = LoggerFactory.getLogger(RecipeManagementBackendApplication.class);

    private MeasurementUnitService measurementUnitService;

    @Autowired
    public MeasurementUnitController(MeasurementUnitService measurementUnitService) {
        this.measurementUnitService = measurementUnitService;
    }

    @GetMapping("/measurement-unit/{id}")
    public MeasurementUnit getMeasurementUnitById(@PathVariable Long id) {
        LOG.info("getMeasurementUnitById: " + id);
        MeasurementUnit measurementUnit = measurementUnitService.getMeasurementUnitById(id);
        return measurementUnit;
    }

    @GetMapping("/measurement-units")
    public List<MeasurementUnit> getAllMeasurementUnits() {
        LOG.info("getAllMeasurementUnits");
        return measurementUnitService.getAllMeasurementUnits();
    }

    @PostMapping("/measurement-unit")
    public void saveMeasurementUnit(@RequestBody MeasurementUnit measurementUnit) {
        LOG.info("saveMeasurementUnit: " + measurementUnit);
        measurementUnitService.saveMeasurementUnit(measurementUnit);
    }
}
