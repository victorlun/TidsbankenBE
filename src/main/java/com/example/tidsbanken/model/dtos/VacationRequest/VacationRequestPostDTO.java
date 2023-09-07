package com.example.tidsbanken.model.dtos.VacationRequest;

import com.example.tidsbanken.enumerator.VacationStatus;
import com.example.tidsbanken.enumerator.VacationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class VacationRequestPostDTO {

    @Schema(description = "The ID of the period the request is regarding.", example = "2")
    private Long periodId;

    @Schema(description = "The ID of the employee the request is regarding.", example = "12345")
    private Long employeeId;

    @Schema(description = "The status of the vacation request.", example = "VacationStatus.PENDING")
    private VacationStatus vacationStatus;

    @Schema(description = "The specified type for the vacation requested.", example = "VacationType.PARENTAL_LEAVE")
    private VacationType vacationType;

    @Schema(description = "Notes for the vacation request.", example = "Parental leave due to closed kindergarten.")
    private String notes;
}
