package com.tidsbanken.services.vacation_request;

import com.tidsbanken.models.entities.VacationRequest;
import com.tidsbanken.repositories.VacationRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

class VacationRequestServiceImplTest {

    @Mock
    private VacationRequestRepository vacationRequestRepository;

    private VacationRequestServiceImpl vacationRequestService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        vacationRequestService = new VacationRequestServiceImpl(vacationRequestRepository);
    }

    @Test
    public void testFindById_ExistingVacationRequest() {
        Long vacationRequestId = 1L;
        VacationRequest mockVacationRequest = new VacationRequest();
        when(vacationRequestRepository.findById(vacationRequestId)).thenReturn(Optional.of(mockVacationRequest));
        VacationRequest result = vacationRequestService.findById(vacationRequestId);
        assertEquals(mockVacationRequest, result);
    }

    @Test
    public void testFindById_NonExistingVacationRequest() {
        Long vacationRequestId = 1L;
        when(vacationRequestRepository.findById(vacationRequestId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            vacationRequestService.findById(vacationRequestId);
        });
    }

    @Test
    public void testFindAll() {
        List<VacationRequest> mockVacationRequests = new ArrayList<>();
        mockVacationRequests.add(new VacationRequest());
        mockVacationRequests.add(new VacationRequest());
        when(vacationRequestRepository.findAll()).thenReturn(mockVacationRequests);
        Collection<VacationRequest> result = vacationRequestService.findAll();
        assertEquals(mockVacationRequests, result);
    }

    @Test
    public void testAddVacationRequest() {
        VacationRequest mockVacationRequest = new VacationRequest();
        when(vacationRequestRepository.save(mockVacationRequest)).thenReturn(mockVacationRequest);
        VacationRequest result = vacationRequestService.add(mockVacationRequest);
        assertEquals(mockVacationRequest, result);
    }

    @Test
    public void testUpdateVacationRequest() {
        VacationRequest mockVacationRequest = new VacationRequest();
        vacationRequestService.update(mockVacationRequest);
        verify(vacationRequestRepository).save(mockVacationRequest);
    }

    @Test
    public void testDeleteById() {
        Long vacationRequestId = 1L;
        vacationRequestService.deleteById(vacationRequestId);
        verify(vacationRequestRepository).deleteById(vacationRequestId);
    }

    @Test
    public void testFindByEmployeeId() {
        Long employeeId = 1L;
        List<VacationRequest> mockVacationRequests = new ArrayList<>();
        when(vacationRequestRepository.findAll()).thenReturn(mockVacationRequests);
        List<VacationRequest> result = vacationRequestService.findByEmployeeId(employeeId);
        assertEquals(mockVacationRequests, result);
    }

    @Test
    public void testFindByManagerId() {
        Long managerId = 1L;
        List<VacationRequest> mockVacationRequests = new ArrayList<>();
        when(vacationRequestRepository.findByEmployee_Manager_EmployeeId(managerId)).thenReturn(mockVacationRequests);
        List<VacationRequest> result = vacationRequestService.findByManagerId(managerId);
        assertEquals(mockVacationRequests, result);
    }
}
