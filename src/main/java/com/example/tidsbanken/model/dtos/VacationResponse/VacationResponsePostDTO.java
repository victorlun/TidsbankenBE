package com.example.tidsbanken.model.dtos.VacationResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class VacationResponsePostDTO {
    private String responseComment;
}
