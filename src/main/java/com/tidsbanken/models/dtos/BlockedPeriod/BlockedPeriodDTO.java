package com.tidsbanken.models.dtos.BlockedPeriod;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BlockedPeriodDTO {
    private Long blockedPeriodId;

    private LocalDate startDate;

    private LocalDate endDate;

    //private Employee employee;

}
