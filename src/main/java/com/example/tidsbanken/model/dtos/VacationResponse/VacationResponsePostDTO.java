package com.example.tidsbanken.model.dtos.VacationResponse;

import com.example.tidsbanken.enumerator.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class VacationResponsePostDTO {
    @Schema(description = "ID of the associated vacation request")
    private Long vacationRequestId;

    @Schema(description = "Response to the vacation request", example = "APPROVED")
    private Response response;

    @Schema(description = "Response comment", example = "Your vacation request has been approved.")
    private String responseComment;
}
