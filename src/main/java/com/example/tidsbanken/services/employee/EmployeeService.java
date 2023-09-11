package com.example.tidsbanken.services.employee;

import com.example.tidsbanken.model.dtos.Employee.EmployeeWithRequestsDTO;
import com.example.tidsbanken.model.entities.Employee;
import com.example.tidsbanken.services.CrudService;

import java.util.List;

public interface EmployeeService extends CrudService<Employee, Long> {
    List<EmployeeWithRequestsDTO> getUnhandledRequestsUnderManager(Long managerId);


}
