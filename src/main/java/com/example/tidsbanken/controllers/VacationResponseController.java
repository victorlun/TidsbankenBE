package com.example.tidsbanken.controllers;

import com.example.tidsbanken.model.entities.VacationResponse;
import com.example.tidsbanken.services.vacation_request.VacationRequestService;
import com.example.tidsbanken.services.vacation_response.VacationResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/response")
public class VacationResponseController {

    private final VacationResponseService vacationResponseService;
    private final VacationRequestService vacationRequestService;

    @Autowired
    public VacationResponseController(VacationResponseService vacationResponseService, VacationRequestService vacationRequestService) {
        this.vacationResponseService = vacationResponseService;
        this.vacationRequestService = vacationRequestService;
    }

    @GetMapping
    public ResponseEntity<List<VacationResponse>> getAll() {
        List<VacationResponse> vacationResponses = vacationResponseService.findAll().stream().toList();
        return new ResponseEntity<>(vacationResponses, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<VacationResponse> getById(@PathVariable Long id) {
        VacationResponse vacationResponse = vacationResponseService.findById(id);
        return new ResponseEntity<>(vacationResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VacationResponse> createResponse(@RequestBody VacationResponse vacationResponse) {
        try {
            vacationResponseService.add(vacationResponse);
            return new ResponseEntity<>(vacationResponse, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateResponse(@PathVariable Long id, @RequestBody VacationResponse newVacationResponse) {
        VacationResponse oldVacationResponse = vacationResponseService.findById(id);
        if(oldVacationResponse == null){
            return ResponseEntity.notFound().build();
        }
        oldVacationResponse.setVacationResponseId(newVacationResponse.getVacationResponseId());
        oldVacationResponse.setResponse(newVacationResponse.getResponse());
        oldVacationResponse.setResponseComment(newVacationResponse.getResponseComment());
        oldVacationResponse.setVacationRequest(newVacationResponse.getVacationRequest());


        vacationResponseService.update(oldVacationResponse);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable Long id) {
        vacationResponseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
