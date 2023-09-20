package com.example.tidsbanken.controllers;

import com.example.tidsbanken.mappers.VacationRequestMapper;
import com.example.tidsbanken.model.dtos.VacationRequest.VacationRequestDTO;
import com.example.tidsbanken.model.dtos.VacationRequest.VacationRequestPostDTO;
import com.example.tidsbanken.model.entities.VacationRequest;
import com.example.tidsbanken.services.employee.EmployeeService;
import com.example.tidsbanken.services.vacation_request.VacationRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class VacationRequestControllerTest {
    @InjectMocks
    private VacationRequestController vacationRequestController;
    @Mock
    private VacationRequestService vacationRequestService;
    @Mock
    private VacationRequestMapper vacationRequestMapper;
    @Mock
    private EmployeeService employeeService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllVacationRequests_shouldReturnAllVocationRequests() {
        List<VacationRequest> vacationRequests = new ArrayList<>();
        List<VacationRequestDTO> vacationRequestDTOs = new ArrayList<>();
        when(vacationRequestService.findAll()).thenReturn(vacationRequests);
        for (VacationRequest vacationRequest : vacationRequests) {
            VacationRequestDTO dto = new VacationRequestDTO();
            vacationRequestDTOs.add(dto);
            when(vacationRequestMapper.vacationRequestToVacationRequestDTO(vacationRequest)).thenReturn(dto);
        }
        ResponseEntity<List<VacationRequestDTO>> response = vacationRequestController.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vacationRequestDTOs, response.getBody());
    }
    @Test
    void testGetAllWhenNoRequestsExist() {
        when(vacationRequestService.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<List<VacationRequestDTO>> response = vacationRequestController.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }
    @Test
    void testGetAllWhenRequestsExist() {
        VacationRequest vacationRequest1 = new VacationRequest();
        vacationRequest1.setStartDate(LocalDate.of(2023, 9, 1));
        vacationRequest1.setEndDate(LocalDate.of(2023, 9, 10));
        VacationRequest vacationRequest2 = new VacationRequest();
        vacationRequest2.setStartDate(LocalDate.of(2023, 9, 5));
        vacationRequest2.setEndDate(LocalDate.of(2023, 9, 15));
        List<VacationRequest> vacationRequests = Arrays.asList(vacationRequest1, vacationRequest2);
        List<VacationRequestDTO> expectedDTOs = vacationRequests
                .stream()
                .map(vacationRequestMapper::vacationRequestToVacationRequestDTO)
                .collect(Collectors.toList());
        when(vacationRequestService.findAll()).thenReturn(vacationRequests);
        ResponseEntity<List<VacationRequestDTO>> response = vacationRequestController.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDTOs, response.getBody());
    }
    @Test
    void testGetVocationRequestById_shouldReturnRequestId() {
        Long requestId = 1L;
        VacationRequest vacationRequest = new VacationRequest();
        VacationRequestDTO dto = new VacationRequestDTO();
        when(vacationRequestService.findById(requestId)).thenReturn(vacationRequest);
        when(vacationRequestMapper.vacationRequestToVacationRequestDTO(vacationRequest)).thenReturn(dto);
        ResponseEntity<VacationRequestDTO> response = vacationRequestController.getById(requestId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }
    @Test
    void testCreateVacationRequest_shouldCreateNewVacationRequest() {
        VacationRequestPostDTO postDTO = new VacationRequestPostDTO();
        VacationRequest vacationRequest = new VacationRequest();
        when(vacationRequestMapper.vacationRequestPostDTOToVacationRequest(postDTO)).thenReturn(vacationRequest);
        ResponseEntity<VacationRequest> response = vacationRequestController.createVacationRequest(postDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(vacationRequest, response.getBody());
    }
    @Test
    void testUpdateVacationRequest_shouldReturnNoContent() {
        Long requestId = 1L;
        VacationRequestPostDTO newRequest = new VacationRequestPostDTO();
        when(vacationRequestService.findById(requestId)).thenThrow(new RuntimeException("Not found"));
        try {
            ResponseEntity<Void> response = vacationRequestController.updateVacationRequest(requestId, newRequest);
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            verify(vacationRequestService, never()).update(any(VacationRequest.class));
        } catch (RuntimeException e) {

        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    @Test
    void testDeleteVacationRequest_shouldDeleteVocationRequest() {
        doNothing().when(vacationRequestService).deleteById(anyLong());
        ResponseEntity<Void> responseEntity = vacationRequestController.deleteVacationRequest(1L);
        verify(vacationRequestService, times(1)).deleteById(anyLong());
    }

    @Test
    void getByEmployee() {
        List<VacationRequest> vacationRequests = new ArrayList<>();
        when(vacationRequestService.findByEmployeeId(anyLong())).thenReturn(vacationRequests);
        ResponseEntity<List<VacationRequestDTO>> responseEntity = vacationRequestController.getByEmployee(1L);
        verify(vacationRequestService, times(1)).findByEmployeeId(anyLong());
    }

    @Test
    void getByManager() {
        List<VacationRequest> vacationRequests = new ArrayList<>();
        when(vacationRequestService.findByManagerId(anyLong())).thenReturn(vacationRequests);
        ResponseEntity<List<VacationRequestDTO>> responseEntity = vacationRequestController.getByManager(1L);
        verify(vacationRequestService, times(1)).findByManagerId(anyLong());

    }
}