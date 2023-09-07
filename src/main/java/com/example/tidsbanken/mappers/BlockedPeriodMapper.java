package com.example.tidsbanken.mappers;

import com.example.tidsbanken.model.dtos.BlockedPeriod.BlockedPeriodDTO;
import com.example.tidsbanken.model.entities.BlockedPeriod;
import com.example.tidsbanken.model.entities.Period;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class BlockedPeriodMapper {

    @Mapping(source = "blockedPeriodId", target = "blockedPeriodId")
    @Mapping(source = "period.periodId", target = "periodId")
    @Mapping(source = "employee.employeeId", target = "employeeId")
    public abstract BlockedPeriodDTO blockedPeriodToBlockedPeriodDto(BlockedPeriod blockedPeriod);

    public abstract List<BlockedPeriodDTO> blockedPeriodToBlockedPeriodDtoList(List<BlockedPeriod> blockedPeriods);

    @Named("mapPeriodId")
    protected Long mapPeriodId(Period period) {
        return period != null ? period.getPeriodId() : null;
    }
    protected Set<Long> mapPeriodIds(Set<Period> source) {
        if (source == null) return null;
        return source.stream().map(this::mapPeriodId).collect(Collectors.toSet());
    }

}
