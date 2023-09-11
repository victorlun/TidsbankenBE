package com.example.tidsbanken.model.dtos.VacationRequest;

import com.example.tidsbanken.utils.enumerators.VacationStatus;
import com.example.tidsbanken.utils.enumerators.VacationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class VacationRequestPostDTO {

    @Schema(description = "Start date of the request", example = "2023-09-11")
    private LocalDate startDate;

    @Schema(description = "End date of the request", example = "2023-09-13")
    private LocalDate endDate;

    @Schema(description = "The ID of the employee the request is regarding.", example = "12345")
    private Long employeeId;

    @Schema(description = "The status of the vacation request.", example = "PENDING")
    private VacationStatus vacationStatus;

    @Schema(description = "The specified type for the vacation requested.", example = "PARENTAL_LEAVE")
    private VacationType vacationType;

    @Schema(description = "Notes for the vacation request.", example = "Parental leave due to closed kindergarten.")
    private String notes;
}
