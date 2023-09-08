package com.example.tidsbanken.controllers;

import com.example.tidsbanken.model.entities.Employee;
import com.example.tidsbanken.services.employee.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name ="Employee", description = "Endpoints to interact with Employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @CrossOrigin
    @GetMapping
    public ResponseEntity<Collection<Employee>> getAllEmployees() {
        Collection<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        if (isValidEmployee(employee)) {
            Employee newEmployee = employeeService.add(employee);
            return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
        }else {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isValidEmployee(Employee employee) {
        if (employee == null) {
            return false;
        }
        if (employee.getFirstName() == null || employee.getFirstName().isEmpty() ||
                employee.getLastName() == null || employee.getLastName().isEmpty() ||
                employee.getEmail() == null || employee.getEmail().isEmpty()) {
            return false;
        }
        if (!isValidEmail(employee.getEmail())) {
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    @CrossOrigin
    @PreAuthorize("hasAnyRole('user', 'admin')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Employee existingEmployee = employeeService.findById(id);
        if (existingEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        existingEmployee.setEmployeeId(updatedEmployee.getEmployeeId());
        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setRole(updatedEmployee.getRole());
        existingEmployee.setManager(updatedEmployee.getManager());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setAuthRole(updatedEmployee.getAuthRole());

        employeeService.update(existingEmployee);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}


