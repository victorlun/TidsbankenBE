package com.example.tidsbanken.controllers;
import com.example.tidsbanken.mappers.VacationRequestMapper;
import com.example.tidsbanken.model.entities.VacationRequest;
import com.example.tidsbanken.model.dtos.VacationRequest.VacationRequestDTO;
import com.example.tidsbanken.services.vacation_request.VacationRequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/request")
@Tag(name ="VacationRequest", description = "Endpoints to interact with VacationRequests")
public class VacationRequestController {
    private final VacationRequestService vacationRequestService;
    private final VacationRequestMapper vacationRequestMapper;
    @Autowired
    public VacationRequestController(VacationRequestService vacationRequestService, VacationRequestMapper vacationRequestMapper) {
        this.vacationRequestService = vacationRequestService;
        this.vacationRequestMapper = vacationRequestMapper;
    }

    @GetMapping
    public ResponseEntity<List<VacationRequestDTO>> getAll(){
        List<VacationRequest> vacationRequests = vacationRequestService.findAll().stream().toList();
        List<VacationRequestDTO> vacationRequestDTOs = new ArrayList<>();

        for(VacationRequest vacationRequest : vacationRequests){
            vacationRequestDTOs.add(vacationRequestMapper.vacationRequestToVacationRequestDTO(vacationRequest));
        }

        return new ResponseEntity<>(vacationRequestDTOs, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<VacationRequest> getById(@PathVariable Long id){
        VacationRequest vacationRequest = vacationRequestService.findById(id);
        return new ResponseEntity<>(vacationRequest, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<VacationRequest> createVacationRequest(@RequestBody VacationRequest vacationRequest){
        try {
            vacationRequestService.add(vacationRequest);
            return new ResponseEntity<>(vacationRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping
    public ResponseEntity<Void> updateVacationRequest(@PathVariable Long id, @RequestBody VacationRequest newVacationRequest){
        VacationRequest oldVacationRequest = vacationRequestService.findById(id);
        if(oldVacationRequest == null){
            return ResponseEntity.notFound().build();
        }
        oldVacationRequest.setVacationRequestId(newVacationRequest.getVacationRequestId());
        oldVacationRequest.setVacationType(newVacationRequest.getVacationType());
        oldVacationRequest.setEmployee(newVacationRequest.getEmployee());
        oldVacationRequest.setStartDate(newVacationRequest.getStartDate());
        oldVacationRequest.setEndDate(newVacationRequest.getEndDate());
        oldVacationRequest.setNotes(newVacationRequest.getNotes());

        vacationRequestService.update(oldVacationRequest);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteVacationRequest(@PathVariable Long id){
        vacationRequestService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
