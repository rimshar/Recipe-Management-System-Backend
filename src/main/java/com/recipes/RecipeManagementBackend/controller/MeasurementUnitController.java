package com.recipes.RecipeManagementBackend.controller;

import com.recipes.RecipeManagementBackend.RecipeManagementBackendApplication;
import com.recipes.RecipeManagementBackend.model.MeasurementUnit;
import com.recipes.RecipeManagementBackend.service.MeasurementUnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("api/rest/MeasurementUnit.svc")
public class MeasurementUnitController {

    private static Logger LOG = LoggerFactory.getLogger(RecipeManagementBackendApplication.class);

    private MeasurementUnitService measurementUnitService;

    @Autowired
    public MeasurementUnitController(MeasurementUnitService measurementUnitService) {
        this.measurementUnitService = measurementUnitService;
    }

    @GetMapping("/measurement-unit/{id}")
    public MeasurementUnit getMeasurementUnitById(@PathVariable long id) {
        LOG.info("getMeasurementUnitById: " + id);
        return measurementUnitService.getMeasurementUnitById(id);
    }

    @GetMapping("/measurement-units")
    public List<MeasurementUnit> getAllMeasurementUnits() {
        LOG.info("getAllMeasurementUnits");
        return measurementUnitService.getAllMeasurementUnits();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/measurement-unit")
    public void saveMeasurementUnit(@RequestBody MeasurementUnit measurementUnit) {
        LOG.info("saveMeasurementUnit: " + measurementUnit);
        measurementUnitService.saveMeasurementUnit(measurementUnit);
    }
}
