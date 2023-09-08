package com.example.tidsbanken.model.dtos.BlockedPeriod;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class BlockedPeriodDTO {
    @Schema(description = "Blocked period ID")
    private Long blockedPeriodId;

    private LocalDate startDate;

    private LocalDate endDate;

    @Schema(description = "ID of the associated employee")
    private Long employeeId;

}
