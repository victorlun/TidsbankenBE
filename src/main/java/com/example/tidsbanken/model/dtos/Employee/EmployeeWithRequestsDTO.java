package com.example.tidsbanken.model.dtos.Employee;

import com.example.tidsbanken.model.dtos.VacationRequest.VacationRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class EmployeeWithRequestsDTO {
    private Long employeeId;
    private Long managerId;
    private String managerName;
    private List<VacationRequestDTO> requests;
}