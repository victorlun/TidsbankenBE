package com.example.tidsbanken.model;

import com.example.tidsbanken.enumerator.VacationStatus;
import com.example.tidsbanken.enumerator.VacationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "vacation_request")
@Schema(description = "Represents a vacation request")
public class VacationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vacationRequestId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "period_id")
    private Period period;

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

    public VacationRequest(Period period, Employee employee, VacationStatus vacationStatus, VacationType vacationType, String notes) {
      this.period =period;
      this.employee = employee;
      this.vacationStatus = vacationStatus;
      this.vacationType = vacationType;
      this.notes = notes;
}
}