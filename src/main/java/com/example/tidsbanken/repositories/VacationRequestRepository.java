package com.example.tidsbanken.repositories;

import com.example.tidsbanken.model.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {
}
