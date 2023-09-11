package com.example.tidsbanken.model.dtos.Employee;

import com.example.tidsbanken.model.dtos.VacationRequest.VacationRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class EmployeeWithRequestsDTO {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private List<VacationRequestDTO> requests;
}