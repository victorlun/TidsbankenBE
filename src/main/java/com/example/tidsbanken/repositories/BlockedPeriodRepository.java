package com.example.tidsbanken.repositories;

import com.example.tidsbanken.model.entities.BlockedPeriod;
import com.example.tidsbanken.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockedPeriodRepository extends JpaRepository<BlockedPeriod, Long> {
        List<BlockedPeriod> findByEmployee(Employee employee);
}
