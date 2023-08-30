package com.example.tidsbanken.model.dto.employee;

import com.example.tidsbanken.enumerator.AuthRole;
import com.example.tidsbanken.model.entities.Employee;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Data
@Getter
@Setter
public class EmployeeUpdateDTO {
    private long employeeId;
    private Employee manager;
    private String firstName;
    private Set<Employee> subordinates = new HashSet<>();
    private String lastName;

    private String email;

    private String role;

    private AuthRole authRole;
}
