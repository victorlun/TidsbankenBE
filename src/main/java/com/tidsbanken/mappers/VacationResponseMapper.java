package com.tidsbanken.mappers;

import com.tidsbanken.models.dtos.VacationResponse.VacationResponseDTO;
import com.tidsbanken.models.dtos.VacationResponse.VacationResponsePostDTO;
import com.tidsbanken.models.entities.VacationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class VacationResponseMapper {
    @Mapping(source = "vacationRequest.vacationRequestId", target = "vacationRequestId")
    public abstract VacationResponseDTO vacationResponseToDTO(VacationResponse entity);
    @Mapping(target = "vacationRequest.vacationRequestId", source = "vacationRequestId" )
    public abstract VacationResponse PostDTOToVacationResponse(VacationResponsePostDTO dto);
}
