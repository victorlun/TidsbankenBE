package com.tidsbanken.services.vacation_request;

import com.tidsbanken.models.entities.VacationRequest;
import com.tidsbanken.services.CrudService;

import java.util.List;

public interface VacationRequestService extends CrudService<VacationRequest, Long> {
    List<VacationRequest> findByEmployeeId(Long employeeId);

    List<VacationRequest> findByManagerId(Long managerId);
}
