package com.tidsbanken.services.blocked_period;

import com.tidsbanken.models.entities.BlockedPeriod;
import com.tidsbanken.services.CrudService;

import java.util.List;

public interface BlockedPeriodService extends CrudService<BlockedPeriod, Long> {
    List<BlockedPeriod> findByEmployee(Long employeeId);

}
