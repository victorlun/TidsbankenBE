package com.example.tidsbanken.services.vacation_request;

import com.example.tidsbanken.model.entities.VacationRequest;
import com.example.tidsbanken.services.CrudService;

import java.util.List;

public interface VacationRequestService extends CrudService<VacationRequest, Long> {
    List<VacationRequest> findByEmployeeId(Long employeeId);

    List<VacationRequest> findByEmployeeIdApprovedOrPending(Long employeeId);

    List<VacationRequest> findAllApprovedOrPending();
}
