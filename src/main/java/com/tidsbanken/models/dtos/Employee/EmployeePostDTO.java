package com.tidsbanken.models.dtos.Employee;

import com.tidsbanken.utils.enumerators.AuthRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EmployeePostDTO {
    @Schema(description = "ID of the employee", example = "2")
    private Long employeeId;

    @Schema(description = "ID of the manager of the employee", example = "1")
    private Long manager;

    @Schema(description = "First name of the employee", example = "John")
    private String firstName;
    @Schema(description = "Last name of the employee", example = "Doe")
    private String lastName;

    @Schema(description = "Email of the employee", example = "john.doe@gmail.com")
    private String email;

    @Schema(description = "The work role of the employee.", example = "Junior Java Developer")
    private String role;

    @Schema(description = "The authorization role of the employee", example = "MANAGER")
    private AuthRole authRole;


}
