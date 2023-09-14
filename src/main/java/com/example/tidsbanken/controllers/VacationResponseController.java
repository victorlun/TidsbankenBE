package com.example.tidsbanken.controllers;

import com.example.tidsbanken.model.dtos.VacationResponse.VacationResponseDTO;
import com.example.tidsbanken.model.dtos.VacationResponse.VacationResponsePostDTO;
import com.example.tidsbanken.model.entities.VacationResponse;
import com.example.tidsbanken.services.vacation_response.VacationResponseService;
import com.example.tidsbanken.utils.error.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/response")
@Tag(name ="VacationResponse", description = "Endpoints to interact with VacationResponses")
public class VacationResponseController {

    private final VacationResponseService vacationResponseService;


    @Autowired
    public VacationResponseController(VacationResponseService vacationResponseService) {
        this.vacationResponseService = vacationResponseService;

    }
    @Operation(summary = "Get  a list of all vacation responses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = VacationResponseDTO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @GetMapping
    public ResponseEntity<List<VacationResponse>> getAll() {
        List<VacationResponse> vacationResponses = vacationResponseService.findAll().stream().toList();
        return new ResponseEntity<>(vacationResponses, HttpStatus.OK);
    }
    @Operation(summary = "Get a vacation response by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VacationResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @GetMapping("{id}")
    public ResponseEntity<VacationResponse> getById(@PathVariable Long id) {
        VacationResponse vacationResponse = vacationResponseService.findById(id);
        return new ResponseEntity<>(vacationResponse, HttpStatus.OK);
    }
    @Operation(summary = "create a new vacation response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VacationResponsePostDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<VacationResponse> createResponse(@RequestBody VacationResponse vacationResponse) {
        try {
            vacationResponseService.add(vacationResponse);
            return new ResponseEntity<>(vacationResponse, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Update an existing vacation response by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
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
    @Operation(summary = "Delete a vacation request by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable Long id) {
        try {
            vacationResponseService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Log the exception and return an error response
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
