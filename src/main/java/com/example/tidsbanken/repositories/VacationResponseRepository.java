package com.example.tidsbanken.repositories;

import com.example.tidsbanken.model.VacationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationResponseRepository extends JpaRepository<VacationResponse, Long> {
}
