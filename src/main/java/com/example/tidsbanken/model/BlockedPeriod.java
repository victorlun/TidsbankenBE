package com.example.tidsbanken.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "blocked_period")
@Schema(description = "Represents a blocked period")
@Getter
@Setter
public class BlockedPeriod {
    @Id
    @OneToOne
    @JoinColumn (name = "period_id")
    private Period period;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    /*
     * Default constructor
     */
    public BlockedPeriod() {

    }
    /*
     * @param period       The period of the blocked period.
     * @param employee     The manager who issues the blocked period
     */
    public BlockedPeriod(Period period, Employee employee) {
        this.period = period;
        this.employee = employee;
    }
}
