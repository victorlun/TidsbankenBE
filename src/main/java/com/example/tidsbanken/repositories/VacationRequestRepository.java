package com.example.tidsbanken.repositories;

import com.example.tidsbanken.model.entities.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {
    List<VacationRequest> findByEmployee_Manager_EmployeeId(Long managerId);

}
