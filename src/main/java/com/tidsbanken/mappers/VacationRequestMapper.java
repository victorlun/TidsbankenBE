package com.tidsbanken.mappers;

import com.tidsbanken.models.dtos.VacationRequest.VacationRequestPostDTO;
import com.tidsbanken.models.entities.VacationRequest;
import com.tidsbanken.models.dtos.VacationRequest.VacationRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "Spring")
public abstract class VacationRequestMapper {
    @Mappings({
            @Mapping(source = "employee.employeeId", target = "employeeId"),
            @Mapping(source = "vacationResponse.vacationResponseId", target = "vacationResponseId"),
            @Mapping(source = "employee.firstName", target = "firstName"),
            @Mapping(source = "employee.lastName", target = "lastName"),
    })
    public abstract VacationRequestDTO vacationRequestToVacationRequestDTO(VacationRequest vacationRequest);
    @Mappings({
            @Mapping(source = "startDate", target ="startDate"),
            @Mapping(source = "endDate", target ="endDate"),
            @Mapping(source = "employeeId", target = "employee.employeeId")
    })
    public abstract VacationRequest vacationRequestPostDTOToVacationRequest(VacationRequestPostDTO dto);
}
