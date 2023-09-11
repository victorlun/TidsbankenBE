package com.example.tidsbanken.mappers;

import com.example.tidsbanken.enumerator.AuthRole;
import com.example.tidsbanken.model.dtos.Employee.EmployeeDTO;
import com.example.tidsbanken.model.dtos.Employee.EmployeeWithRequestsDTO;
import com.example.tidsbanken.model.dtos.VacationRequest.VacationRequestDTO;
import com.example.tidsbanken.model.entities.Employee;
import com.example.tidsbanken.model.entities.VacationRequest;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    @Mapping(target = "managerId", source = "manager.employeeId")
    @Mapping(target = "subordinates", source = "subordinates")
    public abstract EmployeeDTO employeeToEmployeeDTO(Employee employee);

    @Mapping(target = "manager.employeeId", source = "managerId")
    @Mapping(target = "subordinates", source = "subordinates")
    public abstract Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);

    @Named("mapSubordinatesToIds")
    public Set<Long> mapSubordinatesToIds(Set<Employee> source) {
        if (source == null)
            return null;
        return source.stream()
                .map(Employee::getEmployeeId)
                .collect(Collectors.toSet());
    }

    @Named("mapSubordinatesToEmployees")
    public Set<Employee> mapSubordinatesToEmployees(Set<Long> source) {
        if (source == null)
            return null;
        return source.stream().map(employeeId -> {Employee employee = new Employee();
                    employee.setEmployeeId(employeeId);
                    return employee;
                }).collect(Collectors.toSet());
    }

    @Mapping(source = "employeeId", target = "employeeId")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "vacationRequests", target = "requests")
    public abstract EmployeeWithRequestsDTO employeeToEmployeeWithRequestsDTO(Employee employee);

    @Mappings({
            @Mapping(source = "employee.employeeId", target = "employeeId"),
            @Mapping(source = "vacationResponse.response", target = "vacationResponse"),
            @Mapping(source = "vacationResponse.vacationResponseId", target = "vacationResponseId"),
            @Mapping(source = "employee.firstName", target = "firstName"),
            @Mapping(source = "employee.lastName", target = "lastName"),
    })
    public abstract VacationRequestDTO vacationRequestToVacationRequestDTO(VacationRequest vacationRequest);


}
