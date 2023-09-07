package com.example.tidsbanken.model.dtos.VacationResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class VacationResponseUpdateDTO {
    @Schema(description = "ID of a vacation response", example = "123")
    private long vacationResponseId;

    private String responseComment;
}
