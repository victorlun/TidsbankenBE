package com.example.tidsbanken.mappers;

import com.example.tidsbanken.model.entities.VacationRequest;
import com.example.tidsbanken.model.dtos.VacationRequest.VacationRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "Spring")
public abstract class VacationRequestMapper {
    @Mappings({
            @Mapping(source = "employee.employeeId", target = "employeeId"),
            @Mapping(source = "vacationResponse.response", target = "vacationResponse"),
            @Mapping(source = "vacationResponse.vacationResponseId", target = "vacationResponseId"),
            @Mapping(source = "employee.firstName", target = "firstName"),
            @Mapping(source = "employee.lastName", target = "lastName"),
    })
    public abstract VacationRequestDTO vacationRequestToVacationRequestDTO(VacationRequest vacationRequest);
}
