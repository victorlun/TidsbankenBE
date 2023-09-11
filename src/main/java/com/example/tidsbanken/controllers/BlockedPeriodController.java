package com.example.tidsbanken.controllers;

import com.example.tidsbanken.mappers.BlockedPeriodMapper;
import com.example.tidsbanken.model.dtos.BlockedPeriod.BlockedPeriodDTO;
import com.example.tidsbanken.model.dtos.BlockedPeriod.BlockedPeriodPostDTO;
import com.example.tidsbanken.model.dtos.BlockedPeriod.BlockedPeriodUpdateDTO;
import com.example.tidsbanken.model.entities.BlockedPeriod;
import com.example.tidsbanken.model.entities.Employee;
import com.example.tidsbanken.services.blocked_period.BlockedPeriodService;
import com.example.tidsbanken.services.employee.EmployeeService;
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
    private final EmployeeService employeeService;
    private final BlockedPeriodMapper blockedPeriodMapper;

    @Autowired
    public BlockedPeriodController(BlockedPeriodService blockedPeriodService, EmployeeService employeeService, BlockedPeriodMapper blockedPeriodMapper) {
        this.blockedPeriodService = blockedPeriodService;
        this.employeeService = employeeService;
        this.blockedPeriodMapper = blockedPeriodMapper;
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
    public ResponseEntity<?> createBlockedPeriod(@RequestBody BlockedPeriodPostDTO dto) {
        if (dto == null || employeeService.findById(dto.getEmployeeId()) == null) {
            return new ResponseEntity<>("Invalid input. BlockedPeriod or Employee is null.", HttpStatus.BAD_REQUEST);
        }
        BlockedPeriod blockedPeriod  = blockedPeriodMapper.blockedPeriodPostDTOToBlockedPeriod(dto);
        BlockedPeriod newBlockedPeriod = blockedPeriodService.add(blockedPeriod);
        return new ResponseEntity<>(newBlockedPeriod, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlockedPeriod(@PathVariable Long id, @RequestBody BlockedPeriodUpdateDTO updatedBlockedPeriod) {
        BlockedPeriod existingBlockedPeriod = blockedPeriodService.findById(id);
        if (existingBlockedPeriod != null) {
            if (updatedBlockedPeriod == null || employeeService.findById(updatedBlockedPeriod.getEmployeeId()) == null) {
                // Return a more detailed error message and use HttpStatus.BAD_REQUEST
                return new ResponseEntity<>("Invalid input. BlockedPeriod or Employee is null.", HttpStatus.BAD_REQUEST);
            }
            Employee employee = employeeService.findById(updatedBlockedPeriod.getEmployeeId());

            existingBlockedPeriod.setEmployee(employee);
            existingBlockedPeriod.setStartDate(updatedBlockedPeriod.getStartDate());
            existingBlockedPeriod.setEndDate(updatedBlockedPeriod.getEndDate());
            blockedPeriodService.update(existingBlockedPeriod);
            return ResponseEntity.noContent().build();
        } else {
            // Return a more detailed error message and use HttpStatus.NOT_FOUND
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
