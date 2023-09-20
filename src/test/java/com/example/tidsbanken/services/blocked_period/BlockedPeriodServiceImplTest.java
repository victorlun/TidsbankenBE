package com.example.tidsbanken.services.blocked_period;

import com.example.tidsbanken.model.entities.BlockedPeriod;
import com.example.tidsbanken.model.entities.Employee;
import com.example.tidsbanken.repositories.BlockedPeriodRepository;
import com.example.tidsbanken.services.employee.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BlockedPeriodServiceImplTest {

    @Mock
    private BlockedPeriodRepository blockedPeriodRepository;

    @Mock
    private EmployeeService employeeService;

    private BlockedPeriodServiceImpl blockedPeriodService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        blockedPeriodService = new BlockedPeriodServiceImpl(blockedPeriodRepository, employeeService);
    }

    @Test
    public void testFindById_ExistingBlockedPeriod() {
        // Arrange
        Long blockedPeriodId = 1L;
        BlockedPeriod mockBlockedPeriod = new BlockedPeriod();
        when(blockedPeriodRepository.findById(blockedPeriodId)).thenReturn(Optional.of(mockBlockedPeriod));
        BlockedPeriod result = blockedPeriodService.findById(blockedPeriodId);
        assertEquals(mockBlockedPeriod, result);
    }

    @Test
    public void testFindById_NonExistingBlockedPeriod() {
        Long blockedPeriodId = 1L;
        when(blockedPeriodRepository.findById(blockedPeriodId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            blockedPeriodService.findById(blockedPeriodId);
        });
    }

    @Test
    public void testFindAll() {
        List<BlockedPeriod> mockBlockedPeriods = new ArrayList<>();
        mockBlockedPeriods.add(new BlockedPeriod());
        mockBlockedPeriods.add(new BlockedPeriod());
        when(blockedPeriodRepository.findAll()).thenReturn(mockBlockedPeriods);
        Collection<BlockedPeriod> result = blockedPeriodService.findAll();
        assertEquals(mockBlockedPeriods, result);
    }

    @Test
    public void testAddBlockedPeriod() {
        BlockedPeriod mockBlockedPeriod = new BlockedPeriod();
        when(blockedPeriodRepository.save(mockBlockedPeriod)).thenReturn(mockBlockedPeriod);
        BlockedPeriod result = blockedPeriodService.add(mockBlockedPeriod);
        assertEquals(mockBlockedPeriod, result);
    }

    @Test
    public void testUpdateBlockedPeriod() {
        BlockedPeriod mockBlockedPeriod = new BlockedPeriod();
        blockedPeriodService.update(mockBlockedPeriod);
        verify(blockedPeriodRepository).save(mockBlockedPeriod);
    }

    @Test
    public void testDeleteById() {
        Long blockedPeriodId = 1L;
        blockedPeriodService.deleteById(blockedPeriodId);
        verify(blockedPeriodRepository).deleteById(blockedPeriodId);
    }

    @Test
    public void testFindByEmployee() {
        Long employeeId = 1L;
        Employee mockEmployee = new Employee();
        when(employeeService.findById(employeeId)).thenReturn(mockEmployee);

        List<BlockedPeriod> mockBlockedPeriods = new ArrayList<>();
        when(blockedPeriodRepository.findByEmployee(mockEmployee)).thenReturn(mockBlockedPeriods);
        List<BlockedPeriod> result = blockedPeriodService.findByEmployee(employeeId);
        assertEquals(mockBlockedPeriods, result);
    }

}
