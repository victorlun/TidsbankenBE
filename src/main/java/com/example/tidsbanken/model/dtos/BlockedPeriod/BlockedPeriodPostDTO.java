package com.example.tidsbanken.model.dtos.BlockedPeriod;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BlockedPeriodPostDTO {
    @Schema(description = "ID of the associated period")
    private Long periodId;

    @Schema(description = "ID of the associated employee")
    private Long employeeId;
}
