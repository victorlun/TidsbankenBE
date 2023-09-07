package com.example.tidsbanken.mappers;

import com.example.tidsbanken.enumerator.AuthRole;
import com.example.tidsbanken.model.dtos.Employee.EmployeeDTO;
import com.example.tidsbanken.model.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

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

}
