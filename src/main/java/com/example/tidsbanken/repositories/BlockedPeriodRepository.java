package com.example.tidsbanken.repositories;

import com.example.tidsbanken.model.entities.BlockedPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockedPeriodRepository extends JpaRepository<BlockedPeriod, Long> {
}
