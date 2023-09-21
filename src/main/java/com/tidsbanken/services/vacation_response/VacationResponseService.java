package com.tidsbanken.services.vacation_response;

import com.tidsbanken.models.entities.VacationResponse;
import com.tidsbanken.services.CrudService;

public interface VacationResponseService extends CrudService<VacationResponse, Long> {
    void deleteRequestReference(Long id);
}
