package com.example.tidsbanken.mappers;


import com.example.tidsbanken.model.dtos.Period.PeriodDTO;
import com.example.tidsbanken.model.entities.Period;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PeriodMapper {

    @Mapping(source = "periodId", target = "periodId")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    public abstract PeriodDTO periodToPeriodDTO(Period period);

    @Mapping(source = "periodId", target = "periodId")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    public abstract Period periodDTOToPeriod(PeriodDTO periodDTO);
}

