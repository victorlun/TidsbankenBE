package com.tidsbanken.services.employee;

import com.example.tidsbanken.mappers.EmployeeMapper;
import com.example.tidsbanken.model.entities.Employee;
import com.example.tidsbanken.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeServiceImpl(employeeRepository, employeeMapper);
    }

    @Test
    public void testFindById_ExistingEmployee() {
        Long employeeId = 1L;
        Employee mockEmployee = new Employee();
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(mockEmployee));
        Employee result = employeeService.findById(employeeId);
        assertEquals(mockEmployee, result);
    }

    @Test
    public void testFindById_NonExistingEmployee() {
        Long employeeId = 1L;
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            employeeService.findById(employeeId);
        });
    }

    @Test
    public void testAddEmployee() {
        Employee mockEmployee = new Employee();
        when(employeeRepository.save(mockEmployee)).thenReturn(mockEmployee);
        Employee result = employeeService.add(mockEmployee);
        assertEquals(mockEmployee, result);
    }

    @Test
    public void testUpdateEmployee() {
        Employee mockEmployee = new Employee();
        employeeService.update(mockEmployee);
        verify(employeeRepository).save(mockEmployee);
    }

    @Test
    public void testDeleteById() {
        Long employeeId = 1L;
        employeeService.deleteById(employeeId);
        verify(employeeRepository).deleteById(employeeId);
    }
}
