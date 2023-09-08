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


        @Column(name = "start_date")
        private LocalDate startDate;

        @Column(name = "end_date")
        private LocalDate endDate;

        @OneToOne
        @JoinColumn(name = "employee_id")
        private Employee employee;

    /*
     * Default constructor
     */
    public BlockedPeriod() {

    }
  //  /*
  //   * @param period       The period of the blocked period.
  //   * @param employee     The manager who issues the blocked period
  //   */
  //  public BlockedPeriod(Period period, Employee employee) {
//
  //      this.employee = employee;
  //  }
}
