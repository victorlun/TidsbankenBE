package com.example.tidsbanken.model;

import java.util.HashSet;
import java.util.Set;

import com.example.tidsbanken.enumerator.AuthRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private long employeeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @Schema(description = "Manager of the employee")
    private Employee manager;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Employees managed by this manager")
    private Set<Employee> subordinates = new HashSet<>();

    @Column(name = "first_name")
    @Schema(description = "First name of the employee", example = "John")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @Schema(description = "Last name of the employee", example = "Doe")
    @NotNull
    private String lastName;

    @Column(name = "email")
    @Schema(description = "Email of the employee", example = "john.doe@gmail.com")
    @NotNull
    private String email;

    @Column(name = "work_role")
    @Schema(description = "The work role of the employee.", example = "Junior Java Developer")
    @NotNull
    private String role;

    @Column(name = "auth_role")
    @Schema(description = "The authorization role of the employee", example = "AuthRole.MANAGER")
    @NotNull
    private AuthRole authRole;

    /*
     * Default constructor
     */
    public Employee(){}

    /*
     * Constructor to create a new employee.
     * @param id            The ID of the employee.     (Example: "12345")
     * @param firstName     First name of the employee. (Example: "John")
     * @param lastName      Last name of the employee.  (Example: "Doe")
     * @param email         The email of the employee.  (Example: "john.doe@gmail.com)
     * @param manager       The manager of the employee.(Example: Employee manager)
     * @param role          Work role of an employee    (Example: "Junior Java Developer")
     * @param authRole      Authorization role of the employee (Example: AuthRole.MANAGER)
     */
    public Employee(Long id, String firstName, String lastName, String email, Employee manager, String role, AuthRole authRole){
        this.employeeId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.manager = manager;
        this.role = role;
        this.authRole = authRole;
    }


    // Add a subordinate
    public void addSubordinate(Employee employee) {
        subordinates.add(employee);
        employee.setManager(this);
    }

    // Remove a subordinate
    public void removeSubordinate(Employee employee) {
        subordinates.remove(employee);
        employee.setManager(null);
    }

}
