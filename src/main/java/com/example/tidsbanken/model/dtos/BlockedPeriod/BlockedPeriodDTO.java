package com.example.tidsbanken.model.dtos.BlockedPeriod;

import com.example.tidsbanken.model.entities.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BlockedPeriodDTO {
    private Long blockedPeriodId;

    private LocalDate startDate;

    private LocalDate endDate;

    //private Employee employee;

}
