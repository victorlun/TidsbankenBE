package com.example.tidsbanken.model.entities;

import com.example.tidsbanken.enumerator.Response;
import com.example.tidsbanken.model.entities.VacationRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vacation_response")
@Schema(description = "Represents a response to a vacation request")
@Getter
@Setter
public class VacationResponse {
    @Id
    @Schema(description = "ID of a vacation response", example = "123")
    private long vacationResponseId;

    @Getter
    @OneToOne
    @JoinColumn(name = "vacation_request_id")
    private VacationRequest vacationRequest;

    @Column(name = "vacation_response")
    private Response response;

    @Column(name = "response_comment")
    private String responseComment;

    public VacationResponse(){}

    public VacationResponse(VacationRequest vacationRequest, Response vacationResponse, String responseComment){
        this.vacationRequest = vacationRequest;
        this.response = vacationResponse;
        this.responseComment = responseComment;

    }


}
