package com.tidsbanken.controllers;

import com.tidsbanken.models.dtos.BlockedPeriod.BlockedPeriodDTO;
import com.tidsbanken.models.entities.BlockedPeriod;
import com.tidsbanken.services.blocked_period.BlockedPeriodService;
import com.tidsbanken.mappers.BlockedPeriodMapper;
import com.tidsbanken.models.dtos.BlockedPeriod.BlockedPeriodPostDTO;
import com.tidsbanken.models.dtos.BlockedPeriod.BlockedPeriodUpdateDTO;
import com.tidsbanken.models.entities.Employee;
import com.tidsbanken.services.employee.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    @GetMapping("/{blockedPeriodId}")
    public ResponseEntity<?> getBlockedPeriodById(@PathVariable Long blockedPeriodId) {
        BlockedPeriod blockedPeriod = blockedPeriodService.findById(blockedPeriodId);
        BlockedPeriodDTO dto = blockedPeriodMapper.blockedPeriodToBlockedPeriodDto(blockedPeriod);
        if (blockedPeriod != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("BlockedPeriod with ID " + blockedPeriodId + " not found.",HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @GetMapping
    public ResponseEntity<Collection<BlockedPeriodDTO>> getAllBlockedPeriods() {
        Collection<BlockedPeriod> blockedPeriods = blockedPeriodService.findAll();
        Collection<BlockedPeriodDTO> dtos = new ArrayList<>();

        for(BlockedPeriod blockedPeriod : blockedPeriods){
            dtos.add(blockedPeriodMapper.blockedPeriodToBlockedPeriodDto(blockedPeriod));
        }


        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> createBlockedPeriod(@RequestBody BlockedPeriodPostDTO dto) {
        if (dto == null || employeeService.findById(dto.getManagerId()) == null) {
            return new ResponseEntity<>("Invalid input. BlockedPeriod or Employee is null.", HttpStatus.BAD_REQUEST);
        }
        BlockedPeriod blockedPeriod  = blockedPeriodMapper.blockedPeriodPostDTOToBlockedPeriod(dto);
        BlockedPeriod newBlockedPeriod = blockedPeriodService.add(blockedPeriod);
        return new ResponseEntity<>(newBlockedPeriod, HttpStatus.CREATED);
    }
    @PutMapping("/{blockedPeriodId}")
    public ResponseEntity<?> updateBlockedPeriod(@PathVariable Long blockedPeriodId, @RequestBody BlockedPeriodUpdateDTO updatedBlockedPeriod) {
        BlockedPeriod existingBlockedPeriod = blockedPeriodService.findById(blockedPeriodId);
        if (existingBlockedPeriod != null) {
            if (updatedBlockedPeriod == null || employeeService.findById(updatedBlockedPeriod.getManagerId()) == null) {
                return new ResponseEntity<>("Invalid input. BlockedPeriod or Employee is null.", HttpStatus.BAD_REQUEST);
            }
            Employee employee = employeeService.findById(updatedBlockedPeriod.getManagerId());

            existingBlockedPeriod.setEmployee(employee);
            existingBlockedPeriod.setStartDate(updatedBlockedPeriod.getStartDate());
            existingBlockedPeriod.setEndDate(updatedBlockedPeriod.getEndDate());
            blockedPeriodService.update(existingBlockedPeriod);
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>("BlockedPeriod with ID " + blockedPeriodId + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{blockedPeriodId}")
    public ResponseEntity<?> deleteBlockedPeriod(@PathVariable Long blockedPeriodId) {
        BlockedPeriod blockedPeriod = blockedPeriodService.findById(blockedPeriodId);
        if (blockedPeriod != null) {
            blockedPeriodService.deleteById(blockedPeriodId);
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>("BlockedPeriod with ID " + blockedPeriodId + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<BlockedPeriodDTO>> getByEmployee(@PathVariable Long managerId){
        List<BlockedPeriod> blockedPeriods = blockedPeriodService.findByEmployee(managerId);
        List<BlockedPeriodDTO> dtos = new ArrayList<>();
        for(BlockedPeriod blockedPeriod : blockedPeriods){
            dtos.add(blockedPeriodMapper.blockedPeriodToBlockedPeriodDto(blockedPeriod));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
