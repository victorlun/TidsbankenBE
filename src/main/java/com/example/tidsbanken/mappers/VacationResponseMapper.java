package com.example.tidsbanken.mappers;



import com.example.tidsbanken.model.dtos.VacationResponse.VacationResponseDTO;
import com.example.tidsbanken.model.dtos.VacationResponse.VacationResponsePostDTO;
import com.example.tidsbanken.model.entities.VacationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VacationResponseMapper {

    @Mapping(source = "vacationRequest.vacationRequestId", target = "vacationRequestId")
    VacationResponseDTO vacationResponseToDTO(VacationResponse entity);

    @Mapping(target = "vacationRequest.vacationRequestId", source = "vacationRequestId" )
    VacationResponse PostDTOToVacationResponse(VacationResponsePostDTO dto);
}
