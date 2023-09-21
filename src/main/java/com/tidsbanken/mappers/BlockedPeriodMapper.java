package com.tidsbanken.mappers;

import com.tidsbanken.models.dtos.BlockedPeriod.BlockedPeriodDTO;
import com.tidsbanken.models.dtos.BlockedPeriod.BlockedPeriodPostDTO;
import com.tidsbanken.models.entities.BlockedPeriod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BlockedPeriodMapper {
    @Mapping(source = "blockedPeriodId", target = "blockedPeriodId")
    public abstract BlockedPeriodDTO blockedPeriodToBlockedPeriodDto(BlockedPeriod blockedPeriod);
    public abstract List<BlockedPeriodDTO> blockedPeriodToBlockedPeriodDtoList(List<BlockedPeriod> blockedPeriods);
    @Mapping(source = "managerId", target = "employee.employeeId")
    public abstract BlockedPeriod blockedPeriodPostDTOToBlockedPeriod(BlockedPeriodPostDTO blockedPeriodPostDTO);


}
