package com.tidsbanken.models.dtos.Employee;

import com.tidsbanken.utils.enumerators.AuthRole;
import com.tidsbanken.models.entities.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
public class EmployeeUpdateDTO {
    private long employeeId;
    @Schema(description = "Manager of the employee")
    private Employee manager;

    @Schema(description = "Employees managed by this manager")
    private Set<Employee> subordinates = new HashSet<>();

    @Schema(description = "First name of the employee", example = "John")
    private String firstName;
    @Schema(description = "Last name of the employee", example = "Doe")
    private String lastName;

    @Schema(description = "Email of the employee", example = "john.doe@gmail.com")
    private String email;

    @Schema(description = "The work role of the employee.", example = "Junior Java Developer")
    private String role;

    @Schema(description = "The authorization role of the employee", example = "AuthRole.MANAGER")
    private AuthRole authRole;


}
