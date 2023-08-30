package com.example.tidsbanken.model.dto.blockedPeriod;

import com.example.tidsbanken.model.entities.Employee;
import com.example.tidsbanken.model.entities.Period;
import lombok.Data;

@Data
public class BlockedPeriodDTO {
    private Employee employee;
    private Period period;
}
