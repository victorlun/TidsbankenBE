package com.tidsbanken.services.employee;

import com.tidsbanken.models.entities.Employee;
import com.tidsbanken.services.CrudService;

public interface EmployeeService extends CrudService<Employee, Long> {
    //List<EmployeeWithRequestsDTO> getUnhandledRequestsUnderManager(Long managerId);
    void deleteManagerReference (Employee employee);


}
