package com.example.tidsbanken.mappers;

import com.example.tidsbanken.model.dtos.BlockedPeriod.BlockedPeriodDTO;
import com.example.tidsbanken.model.dtos.BlockedPeriod.BlockedPeriodPostDTO;
import com.example.tidsbanken.model.entities.BlockedPeriod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BlockedPeriodMapper {

    @Mapping(source = "blockedPeriodId", target = "blockedPeriodId")
    //@Mapping(source = "period.periodId", target = "periodId")
    //@Mapping(source = "employee.employeeId", target = "employeeId")
    //@Mapping(source = "employee", target = "employee")
    public abstract BlockedPeriodDTO blockedPeriodToBlockedPeriodDto(BlockedPeriod blockedPeriod);

    public abstract List<BlockedPeriodDTO> blockedPeriodToBlockedPeriodDtoList(List<BlockedPeriod> blockedPeriods);

    @Mapping(source = "managerId", target = "employee.employeeId")
    public abstract BlockedPeriod blockedPeriodPostDTOToBlockedPeriod(BlockedPeriodPostDTO blockedPeriodPostDTO);
  //  @Named("mapPeriodId")
  //  protected Long mapPeriodId(Period period) {
  //      return period != null ? period.getPeriodId() : null;
  //  }
  //  protected Set<Long> mapPeriodIds(Set<Period> source) {
  //      if (source == null) return null;
  //      return source.stream().map(this::mapPeriodId).collect(Collectors.toSet());
  //  }

}
