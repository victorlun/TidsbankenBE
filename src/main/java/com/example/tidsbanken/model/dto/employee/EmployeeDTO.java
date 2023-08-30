package com.example.tidsbanken.model.dto.employee;

import com.example.tidsbanken.enumerator.AuthRole;
import com.example.tidsbanken.model.entities.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Data
@Getter
@Setter
public class EmployeeDTO {
    private long employeeId;
    private Employee manager;
    private String firstName;
    private Set<Employee> subordinates = new HashSet<>();
    private String lastName;

    private String email;

    private String role;

    private AuthRole authRole;

}
