package com.tidsbanken.repositories;

import com.tidsbanken.models.entities.VacationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationResponseRepository extends JpaRepository<VacationResponse, Long> {
}
