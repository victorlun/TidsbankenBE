package com.tidsbanken.services.employee;

import com.tidsbanken.mappers.EmployeeMapper;
import com.tidsbanken.models.entities.Employee;
import com.tidsbanken.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
    public void deleteManagerReference(Employee employee) {
        employee.setManager(null);
    }

}
