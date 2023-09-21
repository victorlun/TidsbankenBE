package com.tidsbanken.controllers;

import com.tidsbanken.mappers.VacationResponseMapper;
import com.tidsbanken.models.dtos.VacationResponse.VacationResponseDTO;
import com.tidsbanken.models.dtos.VacationResponse.VacationResponsePostDTO;
import com.tidsbanken.models.entities.VacationResponse;
import com.tidsbanken.services.vacation_response.VacationResponseService;
import com.tidsbanken.utils.error.ApiErrorResponse;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/responses")
@Tag(name ="VacationResponse", description = "Endpoints to interact with VacationResponses")
public class VacationResponseController {
    private final VacationResponseService vacationResponseService;
    private final VacationResponseMapper vacationResponseMapper;
    @Autowired
    public VacationResponseController(VacationResponseService vacationResponseService, VacationResponseMapper vacationResponseMapper) {
        this.vacationResponseService = vacationResponseService;

        this.vacationResponseMapper = vacationResponseMapper;
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
    public ResponseEntity<List<VacationResponseDTO>> getAll() {
        List<VacationResponse> vacationResponses = vacationResponseService.findAll().stream().toList();
        List<VacationResponseDTO> vacationResponseDTOs = new ArrayList<>();
        for(VacationResponse vacationResponse : vacationResponses){
            vacationResponseDTOs.add(vacationResponseMapper.vacationResponseToDTO(vacationResponse));
        }
        return new ResponseEntity<>(vacationResponseDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Get a vacation response by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VacationResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @GetMapping("{responseId}")
    public ResponseEntity<VacationResponseDTO> getById(@PathVariable Long responseId) {
        VacationResponse vacationResponse = vacationResponseService.findById(responseId);
        VacationResponseDTO dto = vacationResponseMapper.vacationResponseToDTO(vacationResponse);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "create a new vacation response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VacationResponsePostDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<VacationResponse> createResponse(@RequestBody VacationResponsePostDTO dto) {
        try {
            VacationResponse vacationResponse = vacationResponseMapper.PostDTOToVacationResponse(dto);

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
    @Operation(summary = "Delete a vacation request by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @DeleteMapping("{responseId}")
    public ResponseEntity<Void> deleteResponse(@PathVariable Long responseId) {
        try {
            vacationResponseService.deleteById(responseId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
