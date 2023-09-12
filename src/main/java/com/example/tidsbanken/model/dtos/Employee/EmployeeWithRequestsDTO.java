package com.example.tidsbanken.model.dtos.Employee;

import com.example.tidsbanken.model.dtos.VacationRequest.VacationRequestDTO;
import lombok.Data;

import java.util.List;
@Data
public class EmployeeWithRequestsDTO {
    private Long employeeId;
    private Long managerId;
    private String managerName;
    private List<VacationRequestDTO> requests;
}