package com.example.tidsbanken.controllers;
import com.example.tidsbanken.mappers.VacationRequestMapper;
import com.example.tidsbanken.model.dtos.VacationRequest.VacationRequestPostDTO;
import com.example.tidsbanken.model.entities.Employee;
import com.example.tidsbanken.model.entities.VacationRequest;
import com.example.tidsbanken.model.dtos.VacationRequest.VacationRequestDTO;
import com.example.tidsbanken.services.employee.EmployeeService;
import com.example.tidsbanken.services.vacation_request.VacationRequestService;
import com.example.tidsbanken.utils.enumerators.VacationType;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/request")
@Tag(name ="VacationRequest", description = "Endpoints to interact with VacationRequests")
public class VacationRequestController {
    private final VacationRequestService vacationRequestService;
    private final VacationRequestMapper vacationRequestMapper;
    private final EmployeeService employeeService;
    @Autowired
    public VacationRequestController(VacationRequestService vacationRequestService, VacationRequestMapper vacationRequestMapper, EmployeeService employeeService) {
        this.vacationRequestService = vacationRequestService;
        this.vacationRequestMapper = vacationRequestMapper;
        this.employeeService = employeeService;
    }
    @Operation(summary = "Get  a list of all vacation requests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = VacationRequestDTO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @GetMapping
    public ResponseEntity<List<VacationRequestDTO>> getAll(){
        List<VacationRequest> vacationRequests = vacationRequestService.findAll().stream().toList();
        List<VacationRequestDTO> vacationRequestDTOs = new ArrayList<>();

        for(VacationRequest vacationRequest : vacationRequests){
            vacationRequestDTOs.add(vacationRequestMapper.vacationRequestToVacationRequestDTO(vacationRequest));
        }

        return new ResponseEntity<>(vacationRequestDTOs, HttpStatus.OK);
    }

    @GetMapping("/approved_or_pending")
    public ResponseEntity<List<VacationRequestDTO>> getAllApprovedOrPending() {
        List<VacationRequest> vacationRequests = vacationRequestService.findAllApprovedOrPending();
        List<VacationRequestDTO> vacationRequestDTOs = new ArrayList<>();

        for (VacationRequest vacationRequest : vacationRequests) {
            vacationRequestDTOs.add(vacationRequestMapper.vacationRequestToVacationRequestDTO(vacationRequest));
        }

        return new ResponseEntity<>(vacationRequestDTOs, HttpStatus.OK);
    }
    @Operation(summary = "Get a vacation request by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VacationRequestDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @GetMapping("{id}")
    public ResponseEntity<VacationRequestDTO> getById(@PathVariable Long id){
        VacationRequest vacationRequest = vacationRequestService.findById(id);
        VacationRequestDTO dto = vacationRequestMapper.vacationRequestToVacationRequestDTO(vacationRequest);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/vacation_types")
    public ResponseEntity<List<VacationType>> getVacationTypes(){
        List<VacationType> types = new ArrayList<>();
        types.add(VacationType.VACATION_LEAVE);
        types.add(VacationType.PARENTAL_LEAVE);
        types.add(VacationType.SICK_LEAVE);
        types.add(VacationType.PUBLIC_HOLIDAY);
        types.add(VacationType.OTHER);

        return new ResponseEntity<>(types, HttpStatus.OK);
    }
    @Operation(summary = "Create a new vacation request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VacationRequestPostDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<VacationRequest> createVacationRequest(@RequestBody VacationRequestPostDTO dto){
        VacationRequest vacationRequest = vacationRequestMapper.vacationRequestPostDTOToVacationRequest(dto);
        try {
            vacationRequestService.add(vacationRequest);
            return new ResponseEntity<>(vacationRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Update an existing Vocation request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PutMapping("{id}")
    public ResponseEntity<Void> updateVacationRequest(@PathVariable Long id, @RequestBody VacationRequestPostDTO newRequest){
        VacationRequest oldVacationRequest = vacationRequestService.findById(id);
        Employee employee = employeeService.findById(newRequest.getEmployeeId());
        if(oldVacationRequest == null || employee == null){
            return ResponseEntity.notFound().build();
        }

        oldVacationRequest.setVacationType(newRequest.getVacationType());
        oldVacationRequest.setEmployee(employee);
        oldVacationRequest.setStartDate(newRequest.getStartDate());
        oldVacationRequest.setEndDate(newRequest.getEndDate());
        oldVacationRequest.setNotes(newRequest.getNotes());

        vacationRequestService.update(oldVacationRequest);

        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Delete a Vacation request by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteVacationRequest(@PathVariable Long id){
        vacationRequestService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Get a Vacation request by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VacationRequestDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @GetMapping("/employee/{id}")
    public ResponseEntity<List<VacationRequestDTO>> getByEmployee(@PathVariable Long id) {
        List<VacationRequest> vacationRequests = vacationRequestService.findByEmployeeId(id);
        List<VacationRequestDTO> vacationRequestDTOs = new ArrayList<>();

        for (VacationRequest vacationRequest : vacationRequests) {
            vacationRequestDTOs.add(vacationRequestMapper.vacationRequestToVacationRequestDTO(vacationRequest));
        }

        return new ResponseEntity<>(vacationRequestDTOs, HttpStatus.OK);
    }
    @GetMapping("/employee/{id}/approved_or_pending")
    public ResponseEntity<List<VacationRequestDTO>> getByEmployeeApprovedOrPending(@PathVariable Long id) {
        List<VacationRequest> vacationRequests = vacationRequestService.findByEmployeeIdApprovedOrPending(id);
        List<VacationRequestDTO> vacationRequestDTOs = new ArrayList<>();

        for (VacationRequest vacationRequest : vacationRequests) {
            vacationRequestDTOs.add(vacationRequestMapper.vacationRequestToVacationRequestDTO(vacationRequest));
        }

        return new ResponseEntity<>(vacationRequestDTOs, HttpStatus.OK);
    }
}
