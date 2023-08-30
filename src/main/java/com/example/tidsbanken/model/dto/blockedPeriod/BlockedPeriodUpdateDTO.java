package com.example.tidsbanken.model.dto.blockedPeriod;

import com.example.tidsbanken.model.entities.Employee;
import com.example.tidsbanken.model.entities.Period;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class BlockedPeriodUpdateDTO {
    private Employee employee;
    private Period period;
}
