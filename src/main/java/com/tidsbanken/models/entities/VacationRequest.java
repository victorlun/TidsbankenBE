package com.tidsbanken.models.entities;

import com.tidsbanken.utils.enumerators.VacationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "vacation_request")
@Schema(description = "Represents a vacation request")
public class VacationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vacationRequestId;

    @OneToOne(mappedBy = "vacationRequest", cascade = CascadeType.ALL)
    private VacationResponse vacationResponse;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(name = "vacation_type", nullable = false)
    @Schema(description = "The specified type for the vacation requested.", example = "VacationType.PARENTAL_LEAVE")
    private VacationType vacationType;

    @Column(name = "notes")
    @Schema(description = "Notes for the vacation request.", example = "Parental leave due to closed kindergarden.")
    private String notes;


}