package com.tidsbanken.models.entities;

import java.util.List;
import com.tidsbanken.utils.enumerators.AuthRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Schema(description = "Represents an employee")
@Getter
@Setter
public class Employee {
    @Id
    @Schema(description = "ID of the employee", example = "12345")
    @Column(name = "employee_id")
    private long employeeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", nullable = false)
    @Schema(description = "Manager of the employee")
    private Employee manager;

    @Column(name = "first_name", nullable = false)
    @Schema(description = "First name of the employee", example = "John")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Schema(description = "Last name of the employee", example = "Doe")
    private String lastName;

    @Column(name = "email", nullable = false)
    @Schema(description = "Email of the employee", example = "john.doe@gmail.com")
    private String email;

    @Column(name = "work_role", nullable = false)
    @Schema(description = "The work role of the employee.", example = "Junior Java Developer")
    private String role;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_role", nullable = false)
    @Schema(description = "The authorization role of the employee", example = "AuthRole.MANAGER")
    private AuthRole authRole;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<VacationRequest> vacationRequests;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<BlockedPeriod> blockedPeriods;

}
