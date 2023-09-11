package com.example.tidsbanken.model.dtos.VacationRequest;

import com.example.tidsbanken.utils.enumerators.VacationStatus;
import com.example.tidsbanken.utils.enumerators.VacationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Data
@Getter
@Setter
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

    @Schema(description = "The status of the vacation request.", example = "VacationStatus.PENDING")
    private VacationStatus vacationStatus;

    @Schema(description = "The specified type for the vacation requested.", example = "VacationType.PARENTAL_LEAVE")
    private VacationType vacationType;

    @Schema(description = "Notes for the vacation request.", example = "Parental leave due to closed kindergarten.")
    private String notes;

    private String vacationResponse;

    private Long vacationResponseId;

}