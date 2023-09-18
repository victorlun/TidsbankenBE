package com.example.tidsbanken.mappers;



import com.example.tidsbanken.model.dtos.VacationResponse.VacationResponseDTO;
import com.example.tidsbanken.model.dtos.VacationResponse.VacationResponsePostDTO;
import com.example.tidsbanken.model.entities.VacationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class VacationResponseMapper {

    @Mapping(source = "vacationRequest.vacationRequestId", target = "vacationRequestId")
    public abstract VacationResponseDTO vacationResponseToDTO(VacationResponse entity);

    @Mapping(target = "vacationRequest.vacationRequestId", source = "vacationRequestId" )
    public abstract VacationResponse PostDTOToVacationResponse(VacationResponsePostDTO dto);
}
