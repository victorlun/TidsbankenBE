package com.example.tidsbanken.model.dtos.Period;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class PeriodPostDTO {
    @Schema(description = "Start date of a period", example = "2023-05-01")
    LocalDate startDate;

    @Schema(description = "End date of a period", example = "2023-05-07")
    LocalDate endDate;
}
