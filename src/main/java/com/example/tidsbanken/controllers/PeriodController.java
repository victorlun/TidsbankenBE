package com.example.tidsbanken.controllers;

import com.example.tidsbanken.model.entities.Period;
import com.example.tidsbanken.services.period.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/periods")
public class PeriodController {
    private final PeriodService periodService;

    @Autowired
    public PeriodController(PeriodService periodService) {
        this.periodService = periodService;
    }

    @GetMapping
    public ResponseEntity<List<Period>> getAll(){
        List<Period> periods = periodService.findAll().stream().toList();
        return new ResponseEntity<>(periods, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Period> getPeriod(@PathVariable Long id){
        Period period = periodService.findById(id);
        return new ResponseEntity<>(period, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Period> createPeriod(@RequestBody Period period) {
        try {
            periodService.add(period);
            return new ResponseEntity<>(period, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updatePeriod(@PathVariable Long id, @RequestBody Period newPeriod){
        Period oldPeriod = periodService.findById(id);
        if(oldPeriod == null){
            return ResponseEntity.notFound().build();
        }

        oldPeriod.setEndDate(newPeriod.getEndDate());
        oldPeriod.setStartDate(newPeriod.getStartDate());

        periodService.update(oldPeriod);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePeriod(@PathVariable Long id){
        periodService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
