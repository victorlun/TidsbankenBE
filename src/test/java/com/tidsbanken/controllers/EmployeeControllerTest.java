//package com.tidsbanken.controllers;
//
//import com.example.tidsbanken.mappers.BlockedPeriodMapper;
//import com.example.tidsbanken.mappers.EmployeeMapper;
//import com.example.tidsbanken.model.dtos.Employee.EmployeeDTO;
//import com.example.tidsbanken.model.dtos.Employee.EmployeePostDTO;
//import com.example.tidsbanken.model.dtos.Employee.EmployeeUpdateDTO;
//import com.example.tidsbanken.model.entities.Employee;
//import com.example.tidsbanken.services.blocked_period.BlockedPeriodService;
//import com.example.tidsbanken.services.employee.EmployeeService;
//import com.example.tidsbanken.utils.error.ApiErrorResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//class EmployeeControllerTest {
//    @InjectMocks
//    private EmployeeController employeeController;
//
//    @Mock
//    private EmployeeService employeeService;
//
//    @Mock
//    private EmployeeDTO employeeDTO;
//    @Mock
//    private EmployeeMapper employeeMapper;
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void testGetAllEmployeesWhenEmployeesExist() {
//        Employee employee1 = new Employee();
//        employee1.setEmployeeId(1L);
//        employee1.setFirstName("John");
//        employee1.setLastName("Doe");
//        employee1.setEmail("john.doe@example.com");
//        Employee employee2 = new Employee();
//        employee2.setEmployeeId(2L);
//        employee2.setFirstName("Jane");
//        employee2.setLastName("Smith");
//        employee2.setEmail("jane.smith@example.com");
//        List<Employee> employees = Arrays.asList(employee1, employee2);
//        List<EmployeeDTO> expectedDTOs = employees
//                .stream()
//                .map(employeeMapper::employeeToEmployeeDTO)
//                .collect(Collectors.toList());
//
//        when(employeeService.findAll()).thenReturn(employees);
//        ResponseEntity<Collection<EmployeeDTO>> response = employeeController.getAllEmployees();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(expectedDTOs, response.getBody());
//    }
//
//    @Test
//    void testGetAllEmployeesWhenNoEmployeesExist() {
//        when(employeeService.findAll()).thenReturn(Collections.emptyList());
//        ResponseEntity<Collection<EmployeeDTO>> response = employeeController.getAllEmployees();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(new ArrayList<EmployeeDTO>(), response.getBody());
//    }
//    @Test
//    void testGetAllEmployeesWhenSingleEmployeeExists() {
//        Employee employee = new Employee();
//        employee.setEmployeeId(1L);
//        employee.setFirstName("John");
//        employee.setLastName("Doe");
//        employee.setEmail("john.doe@example.com");
//
//        List<Employee> employees = Collections.singletonList(employee);
//        EmployeeDTO expectedDTO = employeeMapper.employeeToEmployeeDTO(employee);
//        when(employeeService.findAll()).thenReturn(employees);
//        ResponseEntity<Collection<EmployeeDTO>> response = employeeController.getAllEmployees();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(Collections.singletonList(expectedDTO), response.getBody());
//    }
//    @Test
//    public void testCreateNewEmployee() {
//        EmployeePostDTO employeeDto = new EmployeePostDTO();
//        employeeDto.setFirstName("John");
//        employeeDto.setLastName("Doe");
//        employeeDto.setManager(1L);
//        Employee manager = new Employee();
//        manager.setEmployeeId(1L);
//        Employee employee = new Employee();
//        employee.setEmployeeId(2L);
//        when(employeeService.findById(1L)).thenReturn(manager);
//        when(employeeService.add(any(Employee.class))).thenReturn(employee);
//        when(employeeMapper.employeePostDTOToEmployee(employeeDto, manager)).thenReturn(employee);
//        ResponseEntity<Void> responseEntity = employeeController.createNewEmployee(employeeDto);
//        verify(employeeService).findById(1L);
//        verify(employeeService).add(employee);
//        verify(employeeMapper).employeePostDTOToEmployee(employeeDto, manager);
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        assertEquals("/api/v1/employees" + employee.getEmployeeId(), responseEntity.getHeaders().getLocation().toString());
//    }
//@Test
//    public void testCreateNewEmployee_shouldReturnNewEmployeeSuccess() {
//        EmployeePostDTO employeeDto = new EmployeePostDTO();
//        employeeDto.setFirstName("John");
//        employeeDto.setLastName("Doe");
//        employeeDto.setManager(1L);
//        Employee manager = new Employee();
//        manager.setEmployeeId(1L);
//        Employee employee = new Employee();
//        employee.setEmployeeId(2L);
//        when(employeeService.findById(1L)).thenReturn(manager);
//        when(employeeService.add(any(Employee.class))).thenReturn(employee);
//        when(employeeMapper.employeePostDTOToEmployee(employeeDto, manager)).thenReturn(employee);
//        ResponseEntity<Void> responseEntity = employeeController.createNewEmployee(employeeDto);
//        verify(employeeService).findById(1L);
//        verify(employeeService).add(employee);
//        verify(employeeMapper).employeePostDTOToEmployee(employeeDto, manager);
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        assertEquals("/api/v1/employees" + employee.getEmployeeId(), responseEntity.getHeaders().getLocation().toString());
//    }
//    @Test
//    void testGetEmployeeByIdWhenEmployeeExists() {
//        Long employeeId = 1L;
//        Employee employee = new Employee();
//        employee.setEmployeeId(employeeId);
//
//        EmployeeDTO expectedDTO = new EmployeeDTO();
//        expectedDTO.setEmployeeId(employeeId);
//        when(employeeService.findById(employeeId)).thenReturn(employee);
//        when(employeeMapper.employeeToEmployeeDTO(employee)).thenReturn(expectedDTO);
//        ResponseEntity<EmployeeDTO> responseEntity = employeeController.getEmployeeById(employeeId);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(expectedDTO, responseEntity.getBody());
//    }
//    @Test
//    public void testUpdateEmployeeSuccess() {
//        EmployeeService employeeService = Mockito.mock(EmployeeService.class);
//        EmployeeController employeeController = new EmployeeController(employeeService,employeeMapper);
//        Employee existingEmployee = new Employee();
//        existingEmployee.setEmployeeId(1L);
//        EmployeeUpdateDTO employeeUpdateDTO = new EmployeeUpdateDTO();
//        employeeUpdateDTO.setEmployeeId(1L);
//        when(employeeService.findById(1L)).thenReturn(existingEmployee);
//        ResponseEntity<Void> responseEntity = employeeController.updateEmployee(1L, employeeUpdateDTO);
//        assertEquals(204, responseEntity.getStatusCodeValue());
//    }
//    @Test
//    public void testUpdateEmployeeNotFound_shouldReturnNull() {
//        EmployeeService employeeService = Mockito.mock(EmployeeService.class);
//        EmployeeController employeeController = new EmployeeController(employeeService,employeeMapper);
//        when(employeeService.findById(1L)).thenReturn(null);
//        EmployeeUpdateDTO employeeUpdateDTO = new EmployeeUpdateDTO();
//        ResponseEntity<Void> responseEntity = employeeController.updateEmployee(1L, employeeUpdateDTO);
//        assertEquals(404, responseEntity.getStatusCodeValue());
//    }
//    @Test
//    public void testUpdateEmployeeInvalidId_shouldReturnUpdatedEmployee() {
//        EmployeeService employeeService = Mockito.mock(EmployeeService.class);
//        EmployeeController employeeController = new EmployeeController(employeeService,employeeMapper);
//        Employee existingEmployee = new Employee();
//        existingEmployee.setEmployeeId(1L);
//        EmployeeUpdateDTO employeeUpdateDTO = new EmployeeUpdateDTO();
//        employeeUpdateDTO.setEmployeeId(2L);
//        when(employeeService.findById(1L)).thenReturn(existingEmployee);
//        ResponseEntity<Void> responseEntity = employeeController.updateEmployee(1L, employeeUpdateDTO);
//        assertEquals(400, responseEntity.getStatusCodeValue());
//    }
//    @Test
//    public void testDeleteEmployeeSuccess() {
//        EmployeeService employeeService = Mockito.mock(EmployeeService.class);
//        EmployeeController employeeController = new EmployeeController(employeeService,employeeMapper);
//        Employee existingEmployee = new Employee();
//        existingEmployee.setEmployeeId(1L);
//        when(employeeService.findById(1L)).thenReturn(existingEmployee);
//        ResponseEntity<Void> responseEntity = employeeController.deleteEmployee(1L);
//        assertEquals(204, responseEntity.getStatusCodeValue());
//    }
//    @Test
//    public void testDeleteEmployeeNotFound() {
//        EmployeeService employeeService = Mockito.mock(EmployeeService.class);
//        EmployeeController employeeController = new EmployeeController(employeeService,employeeMapper);
//        when(employeeService.findById(1L)).thenReturn(null);
//        ResponseEntity<Void> responseEntity = employeeController.deleteEmployee(1L);
//        assertEquals(204, responseEntity.getStatusCodeValue());
//    }
//
//}