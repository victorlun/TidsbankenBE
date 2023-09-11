package com.example.tidsbanken.model.entities;

import com.example.tidsbanken.utils.enumerators.VacationStatus;
import com.example.tidsbanken.utils.enumerators.VacationType;
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

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Schema(description = "The status of the vacation request.", example = "VacationStatus.PENDING")
    private VacationStatus vacationStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "vacation_type")
    @Schema(description = "The specified type for the vacation requested.", example = "VacationType.PARENTAL_LEAVE")
    private VacationType vacationType;

    @Column(name = "notes")
    @Schema(description = "Notes for the vacation request.", example = "Parental leave due to closed kindergarden.")
    private String notes;


    public VacationRequest() {}

    public VacationRequest(Employee employee, VacationStatus vacationStatus, VacationType vacationType, String notes) {
      this.employee = employee;
      this.vacationStatus = vacationStatus;
      this.vacationType = vacationType;
      this.notes = notes;
}
}