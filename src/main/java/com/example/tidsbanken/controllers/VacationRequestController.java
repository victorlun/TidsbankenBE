package com.example.tidsbanken.controllers;
import com.example.tidsbanken.model.VacationRequest;
import com.example.tidsbanken.services.vacation_request.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/request")
public class VacationRequestController {
    private final VacationRequestService vacationRequestService;

    @Autowired
    public VacationRequestController(VacationRequestService vacationRequestService) {
        this.vacationRequestService = vacationRequestService;
    }

    @GetMapping
    public ResponseEntity<List<VacationRequest>> getAll(){
        List<VacationRequest> vacationRequests = vacationRequestService.findAll().stream().toList();
        return new ResponseEntity<>(vacationRequests, HttpStatus.OK);
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
        oldVacationRequest.setPeriod(newVacationRequest.getPeriod());
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
