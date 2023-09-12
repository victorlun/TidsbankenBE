package com.example.tidsbanken.controllers;

import com.example.tidsbanken.mappers.EmployeeMapper;
import com.example.tidsbanken.model.dtos.Employee.EmployeeDTO;
import com.example.tidsbanken.model.dtos.Employee.EmployeePostDTO;
import com.example.tidsbanken.model.dtos.Employee.EmployeeUpdateDTO;
import com.example.tidsbanken.model.dtos.Employee.EmployeeWithRequestsDTO;
import com.example.tidsbanken.model.entities.Employee;
import com.example.tidsbanken.services.employee.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name ="Employee", description = "Endpoints to interact with Employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }
    @CrossOrigin
    @GetMapping
    public ResponseEntity<Collection<EmployeeDTO>> getAllEmployees() {
        Collection<Employee> employees = employeeService.findAll();
        Collection<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
          employeeDTOS.add(employeeMapper.employeeToEmployeeDTO(employee));
        }
        return  new ResponseEntity<>(employeeDTOS,HttpStatus.OK);
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
   @PostMapping
   @CrossOrigin
   public ResponseEntity<Void> createNewEmployee(@RequestBody EmployeePostDTO employeeDto) {
        Employee manager = employeeService.findById(employeeDto.getManager());
        Employee employee = employeeService.add(
               employeeMapper.employeePostDTOToEmployee(employeeDto, manager));
       URI location = URI.create("/api/v1/employees" +employee.getEmployeeId());
       return ResponseEntity.created(location).build();
   }


   @CrossOrigin
   //@PreAuthorize("hasAnyRole('user', 'admin')")
   @PutMapping("/{id}")
   public ResponseEntity<Void> updateEmployee(@PathVariable Long id, @RequestBody EmployeeUpdateDTO employeeUpdateDTO) {
       Employee existingEmployee = employeeService.findById(id);

       if (existingEmployee == null) {
           return ResponseEntity.notFound().build();
       }

       if (!id.equals(employeeUpdateDTO.getEmployeeId())) {
           return ResponseEntity.badRequest().build();
       }
       existingEmployee.setFirstName(employeeUpdateDTO.getFirstName());
       existingEmployee.setLastName(employeeUpdateDTO.getLastName());
       existingEmployee.setRole(employeeUpdateDTO.getRole());
       existingEmployee.setManager(employeeUpdateDTO.getManager());
       existingEmployee.setEmail(employeeUpdateDTO.getEmail());
       existingEmployee.setAuthRole(employeeUpdateDTO.getAuthRole());

       employeeService.update(existingEmployee);

       return ResponseEntity.noContent().build();
   }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/manager/{id}/unhandled")
    public ResponseEntity<List<EmployeeWithRequestsDTO>> getUnhandledRequestsUnderManager(@PathVariable Long id) {
        List<EmployeeWithRequestsDTO> employeeWithRequests = employeeService.getUnhandledRequestsUnderManager(id);
        if (employeeWithRequests == null || employeeWithRequests.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(employeeWithRequests, HttpStatus.OK);
        }



    }
