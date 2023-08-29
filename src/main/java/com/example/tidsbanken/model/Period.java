package com.example.tidsbanken.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name = "period")
@Schema(description = "Represents a period")
@Getter
@Setter
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID of the period", example = "123")
    private Long periodId;

    @Column(name = "start_date")
    @Schema(description = "Start date of a period", example = "2023-05-01")
    LocalDate startDate;

    @Column(name = "end_date")
    @Schema(description = "End date of a period", example = "2023-05-07")
    LocalDate endDate;

    /*
     * Default constructor
     */
    public Period(){}

    /*
     * Constructor to create a period
     * @param startDate     The start date for the period (Example: 2023-05-01)
     * @param endDate       The end date for the period   (Example: 2023-05-07)
     */
    public Period (LocalDate startDate, LocalDate endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
