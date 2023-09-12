package com.example.tidsbanken.mappers;

import com.example.tidsbanken.model.dtos.Employee.EmployeeDTO;
import com.example.tidsbanken.model.dtos.Employee.EmployeePostDTO;
import com.example.tidsbanken.model.dtos.Employee.EmployeeUpdateDTO;
import com.example.tidsbanken.model.dtos.Employee.EmployeeWithRequestsDTO;
import com.example.tidsbanken.model.dtos.VacationRequest.VacationRequestDTO;
import com.example.tidsbanken.model.entities.Employee;
import com.example.tidsbanken.model.entities.VacationRequest;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

   // @Mapping(target = "managerId", source = "manager.employeeId")
    //@Mapping(target = "subordinates", source = "subordinates")
    public abstract EmployeeDTO employeeToEmployeeDTO(Employee employee);
    @Mapping(target = "manager.employeeId", source = "manager")
    public abstract  Employee employeePostDTOToEmployee(EmployeePostDTO employeePostDTO);

    @AfterMapping
    protected void mapManager(EmployeePostDTO dto, @MappingTarget Employee entity) {
        if (dto.getManager() != null) {
            Employee manager = new Employee();
            manager.setEmployeeId(dto.getManager());
        entity.setManager(manager);
        }
    }
    @Mapping(target = "manager.employeeId", source = "managerName")
    //@Mapping(target = "subordinates", source = "subordinates")
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
    @Mapping(source = "manager.employeeId", target = "managerId")
    @Mapping(source = "vacationRequests", target = "requests")
    public abstract EmployeeWithRequestsDTO employeeToEmployeeWithRequestsDTO(Employee employee);

    @AfterMapping
    protected void fillManagerName(Employee employee, @MappingTarget EmployeeWithRequestsDTO dto) {
        if (employee.getManager() != null) {
            dto.setManagerName(employee.getManager().getFirstName() + " " + employee.getManager().getLastName());
        }
    }


    @Mappings({
            @Mapping(source = "employee.employeeId", target = "employeeId"),
            @Mapping(source = "vacationResponse.response", target = "vacationResponse"),
            @Mapping(source = "vacationResponse.vacationResponseId", target = "vacationResponseId"),
            @Mapping(source = "employee.firstName", target = "firstName"),
            @Mapping(source = "employee.lastName", target = "lastName"),
    })
    public abstract VacationRequestDTO vacationRequestToVacationRequestDTO(VacationRequest vacationRequest);

    public abstract Employee employeeUpdateDtoToEmployee(EmployeeUpdateDTO employeeUpdateDTO);

}
