package com.example.tidsbanken.controllers;

import com.example.tidsbanken.error.ApiErrorResponse;
import com.example.tidsbanken.model.dtos.BlockedPeriod.BlockedPeriodDTO;
import com.example.tidsbanken.model.entities.BlockedPeriod;
import com.example.tidsbanken.services.blocked_period.BlockedPeriodService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@RestController
@RequestMapping("/api/v1/blocked-periods")
@Tag(name ="BlockedPeriod", description = "Endpoints to interact with BlockedPeriods")
public class BlockedPeriodController {
    private final BlockedPeriodService blockedPeriodService;

    @Autowired
    public BlockedPeriodController(BlockedPeriodService blockedPeriodService) {
        this.blockedPeriodService = blockedPeriodService;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getBlockedPeriodById(@PathVariable Long id) {
        BlockedPeriod blockedPeriod = blockedPeriodService.findById(id);
        if (blockedPeriod != null) {
            return new ResponseEntity<>(blockedPeriod, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("BlockedPeriod with ID " + id + " not found.",HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @GetMapping
    public ResponseEntity<Collection<BlockedPeriod>> getAllBlockedPeriods() {
        Collection<BlockedPeriod> blockedPeriods = blockedPeriodService.findAll();
        return new ResponseEntity<>(blockedPeriods, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> createBlockedPeriod(@RequestBody BlockedPeriod blockedPeriod) {
        if (blockedPeriod == null || blockedPeriod.getEmployee() == null) {
            return new ResponseEntity<>("Invalid input. BlockedPeriod or Employee is null.", HttpStatus.BAD_REQUEST);
        }
        BlockedPeriod newBlockedPeriod = blockedPeriodService.add(blockedPeriod);
        return new ResponseEntity<>(newBlockedPeriod, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlockedPeriod(@PathVariable Long id, @RequestBody BlockedPeriod updatedBlockedPeriod) {
        BlockedPeriod existingBlockedPeriod = blockedPeriodService.findById(id);
        if (existingBlockedPeriod != null) {
            if (updatedBlockedPeriod == null || updatedBlockedPeriod.getEmployee() == null) {
                return new ResponseEntity<>("Invalid input. BlockedPeriod or Employee is null.", HttpStatus.BAD_REQUEST);
            }
            existingBlockedPeriod.setEmployee(updatedBlockedPeriod.getEmployee());
            existingBlockedPeriod.setBlockedPeriodId(updatedBlockedPeriod.getBlockedPeriodId());
            blockedPeriodService.update(existingBlockedPeriod);
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>("BlockedPeriod with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlockedPeriod(@PathVariable Long id) {
        BlockedPeriod blockedPeriod = blockedPeriodService.findById(id);
        if (blockedPeriod != null) {
            blockedPeriodService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>("BlockedPeriod with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
