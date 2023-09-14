package com.example.tidsbanken.services.blocked_period;

import com.example.tidsbanken.model.entities.BlockedPeriod;
import com.example.tidsbanken.model.entities.Employee;
import com.example.tidsbanken.repositories.BlockedPeriodRepository;
import com.example.tidsbanken.services.employee.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BlockedPeriodServiceImpl implements BlockedPeriodService {
    private final BlockedPeriodRepository blockedPeriodRepository;
    private final EmployeeService employeeService;

    public BlockedPeriodServiceImpl(BlockedPeriodRepository blockedPeriodRepository, EmployeeService employeeService) {
        this.blockedPeriodRepository = blockedPeriodRepository;

        this.employeeService = employeeService;
    }

    @Override
    public BlockedPeriod findById(Long id) {
        return blockedPeriodRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public Collection<BlockedPeriod> findAll() {
        return blockedPeriodRepository.findAll();
    }

    @Override
    public BlockedPeriod add(BlockedPeriod blockedPeriod) {
        return blockedPeriodRepository.save(blockedPeriod);
    }

    @Override
    public void update(BlockedPeriod blockedPeriod) {
        blockedPeriodRepository.save(blockedPeriod);
    }

    @Override
    public void deleteById(Long id) {
        blockedPeriodRepository.deleteById(id);

    }

    @Override
    public List<BlockedPeriod> findByEmployee(Long employeeId) {
        Employee manager = employeeService.findById(employeeId);
        return blockedPeriodRepository.findByEmployee(manager);
    }
}
