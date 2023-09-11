package com.example.tidsbanken.model.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "blocked_period")
@Schema(description = "Represents a blocked period")
@Getter
@Setter
public class BlockedPeriod {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long blockedPeriodId; // New ID

        @Column(name = "start_date", nullable = false)
        @Schema(description = "Start date of the blocked period", example = "2023-09-12")
        private LocalDate startDate;

        @Column(name = "end_date", nullable = false)
        @Schema(description = "End date of the blocked period", example = "2023-09-15")
        private LocalDate endDate;

       @ManyToOne
       @JoinColumn(name = "employee_id_bp", nullable = false)
       private Employee employee;
}
