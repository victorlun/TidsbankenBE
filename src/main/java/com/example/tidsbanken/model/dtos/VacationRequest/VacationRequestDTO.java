package com.example.tidsbanken.model.dtos.VacationRequest;

import com.example.tidsbanken.utils.enumerators.VacationStatus;
import com.example.tidsbanken.utils.enumerators.VacationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;


@Data

public class VacationRequestDTO {
    private Long vacationRequestId;

    @Schema(description = "The start date of the request")
    private LocalDate startDate;

    @Schema(description = "The end date of the request")
    private LocalDate endDate;

    @Schema(description = "The ID of the employee the request is regarding.", example = "12345")
    private Long employeeId;

    private String firstName;

    private String lastName;

    @Schema(description = "The specified type for the vacation requested.", example = "VacationType.PARENTAL_LEAVE")
    private VacationType vacationType;

    @Schema(description = "Notes for the vacation request.", example = "Parental leave due to closed kindergarten.")
    private String notes;

    private Long vacationResponseId;

}