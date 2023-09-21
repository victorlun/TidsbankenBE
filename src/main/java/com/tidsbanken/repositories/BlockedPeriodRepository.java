package com.tidsbanken.repositories;

import com.tidsbanken.models.entities.BlockedPeriod;
import com.tidsbanken.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockedPeriodRepository extends JpaRepository<BlockedPeriod, Long> {
        List<BlockedPeriod> findByEmployee(Employee employee);
}
