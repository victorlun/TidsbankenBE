package com.example.tidsbanken.model.dtos.Employee;

import com.example.tidsbanken.utils.enumerators.AuthRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmployeeDTO {
    private long employeeId;

    //@Schema(description = "ID of the manager")
   // private Long managerId;

    @Schema(description = "Manager's name")
    private String managerName;

    //@Schema(description = "Employees managed by this manager")
   // private Set<Employee> subordinates = new HashSet<>();

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
