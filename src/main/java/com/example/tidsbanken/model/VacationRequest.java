package com.example.tidsbanken.model;

import com.example.tidsbanken.enumerator.VacationStatus;
import com.example.tidsbanken.enumerator.VacationType;
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

    @OneToOne
    @JoinColumn(name = "period_id")
    @Schema(description = "The ID of the period the request is regarding.", example = "2")
    private Period period;

    @OneToOne
    @JoinColumn(name = "employee_id")
    @Schema(description = "The ID of the employee the request is regarding.", example = "12345")
    private Employee employee;

    @Column(name = "status")
    @Schema(description = "The status of the vacation request.", example = "VacationStatus.PENDING")
    private VacationStatus vacationStatus;
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