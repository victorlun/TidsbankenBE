package com.example.tidsbanken.services.employee;

import com.example.tidsbanken.mappers.EmployeeMapper;
import com.example.tidsbanken.model.dtos.Employee.EmployeeWithRequestsDTO;
import com.example.tidsbanken.model.dtos.VacationRequest.VacationRequestDTO;
import com.example.tidsbanken.model.entities.Employee;
import com.example.tidsbanken.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public Collection<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee add(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeWithRequestsDTO> getUnhandledRequestsUnderManager(Long managerId) {
        // Find the manager with the given ID
        Employee manager = employeeRepository.findById(managerId).orElse(null);
        if (manager == null) {
            return null;
        }

        // Find all employees under that manager
        List<Employee> employees = employeeRepository.findByManager(manager);

        // Convert Employee to EmployeeWithRequestsDTO
        List<EmployeeWithRequestsDTO> allEmployees = employees.stream()
                .map(employeeMapper::employeeToEmployeeWithRequestsDTO)
                .collect(Collectors.toList());

        // Return a filtered list with only Pending requests
        return filterEmployeesAndRequests(allEmployees);
    }

    @Override
    public void deleteManagerReference(Employee employee) {
        employee.setManager(null);
    }

    public List<EmployeeWithRequestsDTO> filterEmployeesAndRequests(List<EmployeeWithRequestsDTO> allEmployees) {
        return allEmployees.stream()
                .filter(employee -> employee.getRequests() != null && !employee.getRequests().isEmpty())  // filter out employees with empty or null requests
                .map(employee -> {
                    List<VacationRequestDTO> filteredRequests = employee.getRequests().stream()
                            .filter(request -> Objects.equals(request.getVacationResponse(), null))
                            .collect(Collectors.toList());
                    employee.setRequests(filteredRequests);
                    return employee;
                })
                .filter(employee -> !employee.getRequests().isEmpty())  // filter out employees who have empty requests after previous filtering
                .collect(Collectors.toList());
    }
}
