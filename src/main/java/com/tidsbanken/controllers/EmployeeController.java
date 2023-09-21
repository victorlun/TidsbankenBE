package com.tidsbanken.controllers;

import com.tidsbanken.mappers.EmployeeMapper;
import com.tidsbanken.models.dtos.Employee.EmployeeDTO;
import com.tidsbanken.models.dtos.Employee.EmployeePostDTO;
import com.tidsbanken.models.dtos.Employee.EmployeeUpdateDTO;
import com.tidsbanken.models.entities.Employee;
import com.tidsbanken.services.employee.EmployeeService;
import com.tidsbanken.utils.error.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

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
    @Operation(summary = "Get  a list of all Employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EmployeeDTO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
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
    @Operation(summary = "Get a Employee by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @CrossOrigin
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        EmployeeDTO dto = employeeMapper.employeeToEmployeeDTO(employee);
        if (employee != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Operation(summary = "Create a new Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeePostDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
   @PostMapping
   @CrossOrigin
   public ResponseEntity<Void> createNewEmployee(@RequestBody EmployeePostDTO employeeDto) {
        Employee manager = employeeService.findById(employeeDto.getManager());
        Employee employee = employeeService.add(
               employeeMapper.employeePostDTOToEmployee(employeeDto, manager));
       URI location = URI.create("/api/v1/employees" +employee.getEmployeeId());
       return ResponseEntity.created(location).build();
   }
    @Operation(summary = "Update an existing employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
   @CrossOrigin
   //@PreAuthorize("hasAnyRole('user', 'admin')")
   @PutMapping("/{employeeId}")
   public ResponseEntity<Void> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeUpdateDTO employeeUpdateDTO) {
       Employee existingEmployee = employeeService.findById(employeeId);

       if (existingEmployee == null) {
           return ResponseEntity.notFound().build();
       }

       if (!employeeId.equals(employeeUpdateDTO.getEmployeeId())) {
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
    @Operation(summary = "Delete a Employee by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        employeeService.deleteManagerReference(employee);
        employeeService.deleteById(employeeId);
        return ResponseEntity.noContent().build();
    }





    }
