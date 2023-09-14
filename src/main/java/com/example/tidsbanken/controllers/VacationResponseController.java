package com.example.tidsbanken.controllers;

import com.example.tidsbanken.model.entities.VacationResponse;
import com.example.tidsbanken.services.vacation_response.VacationResponseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/responses")
@Tag(name ="VacationResponse", description = "Endpoints to interact with VacationResponses")
public class VacationResponseController {

    private final VacationResponseService vacationResponseService;


    @Autowired
    public VacationResponseController(VacationResponseService vacationResponseService) {
        this.vacationResponseService = vacationResponseService;

    }

    @GetMapping
    public ResponseEntity<List<VacationResponse>> getAll() {
        List<VacationResponse> vacationResponses = vacationResponseService.findAll().stream().toList();
        return new ResponseEntity<>(vacationResponses, HttpStatus.OK);
    }

    @GetMapping("{responseId}")
    public ResponseEntity<VacationResponse> getById(@PathVariable Long responseId) {
        VacationResponse vacationResponse = vacationResponseService.findById(responseId);
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

    @PutMapping("{responseId}")
    public ResponseEntity<Void> updateResponse(@PathVariable Long responseId, @RequestBody VacationResponse newVacationResponse) {
        VacationResponse oldVacationResponse = vacationResponseService.findById(responseId);
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
    @DeleteMapping("{responseId}")
    public ResponseEntity<Void> deleteResponse(@PathVariable Long responseId) {
        try {
            vacationResponseService.deleteById(responseId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Log the exception and return an error response
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
