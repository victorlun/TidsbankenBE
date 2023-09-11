package com.example.tidsbanken.model.entities;

import com.example.tidsbanken.utils.enumerators.Response;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vacationResponseId;

    @OneToOne
    @JoinColumn(name = "vacation_request_id")
    private VacationRequest vacationRequest;

    @Enumerated(EnumType.STRING)
    @Column(name = "vacation_response",nullable = false)
    private Response response;

    @Column(name = "response_comment",nullable = false)
    private String responseComment;

    public VacationResponse(){}

    public VacationResponse(VacationRequest vacationRequest, Response vacationResponse, String responseComment){
        this.vacationRequest = vacationRequest;
        this.response = vacationResponse;
        this.responseComment = responseComment;

    }


}
