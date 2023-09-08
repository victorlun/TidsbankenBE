package com.example.tidsbanken.model.dtos.BlockedPeriod;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class BlockedPeriodUpdateDTO {
    @Schema(description = "Start date of a blocked period")
    private LocalDate startDate;

    @Schema(description = "End date of a blocked period")
    private LocalDate endDate;


    @Schema(description = "ID of the associated period")
    private Long periodId;

    @Schema(description = "ID of the associated employee")
    private Long employeeId;
}
