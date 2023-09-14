package com.example.tidsbanken.services.blocked_period;

import com.example.tidsbanken.model.entities.BlockedPeriod;
import com.example.tidsbanken.services.CrudService;

import java.util.List;

public interface BlockedPeriodService extends CrudService<BlockedPeriod, Long> {
    List<BlockedPeriod> findByEmployee(Long employeeId);

}
