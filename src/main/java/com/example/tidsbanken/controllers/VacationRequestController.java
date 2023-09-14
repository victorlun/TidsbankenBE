package com.example.tidsbanken.controllers;
import com.example.tidsbanken.mappers.VacationRequestMapper;
import com.example.tidsbanken.model.dtos.VacationRequest.VacationRequestPostDTO;
import com.example.tidsbanken.model.entities.Employee;
import com.example.tidsbanken.model.entities.VacationRequest;
import com.example.tidsbanken.model.dtos.VacationRequest.VacationRequestDTO;
import com.example.tidsbanken.services.employee.EmployeeService;
import com.example.tidsbanken.services.vacation_request.VacationRequestService;
import com.example.tidsbanken.utils.enumerators.VacationType;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/requests")
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
    @GetMapping
    public ResponseEntity<List<VacationRequestDTO>> getAll(){
        List<VacationRequest> vacationRequests = vacationRequestService.findAll().stream().toList();
        List<VacationRequestDTO> vacationRequestDTOs = new ArrayList<>();

        for(VacationRequest vacationRequest : vacationRequests){
            vacationRequestDTOs.add(vacationRequestMapper.vacationRequestToVacationRequestDTO(vacationRequest));
        }

        return new ResponseEntity<>(vacationRequestDTOs, HttpStatus.OK);
    }

    @GetMapping("{requestId}")
    public ResponseEntity<VacationRequestDTO> getById(@PathVariable Long requestId){
        VacationRequest vacationRequest = vacationRequestService.findById(requestId);
        VacationRequestDTO dto = vacationRequestMapper.vacationRequestToVacationRequestDTO(vacationRequest);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


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
    @PutMapping("{requestId}")
    public ResponseEntity<Void> updateVacationRequest(@PathVariable Long requestId, @RequestBody VacationRequestPostDTO newRequest){
        VacationRequest oldVacationRequest = vacationRequestService.findById(requestId);
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

    @DeleteMapping("{requestId}")
    public ResponseEntity<Void> deleteVacationRequest(@PathVariable Long requestId){
        vacationRequestService.deleteById(requestId);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<VacationRequestDTO>> getByEmployee(@PathVariable Long employeeId) {
        List<VacationRequest> vacationRequests = vacationRequestService.findByEmployeeId(employeeId);
        List<VacationRequestDTO> vacationRequestDTOs = new ArrayList<>();

        for (VacationRequest vacationRequest : vacationRequests) {
            vacationRequestDTOs.add(vacationRequestMapper.vacationRequestToVacationRequestDTO(vacationRequest));
        }

        return new ResponseEntity<>(vacationRequestDTOs, HttpStatus.OK);
    }
}
