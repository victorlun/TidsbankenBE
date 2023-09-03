package com.example.tidsbanken.services.period;

import com.example.tidsbanken.model.Period;
import com.example.tidsbanken.repositories.PeriodRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PeriodServiceImpl implements PeriodService {
    private final PeriodRepository periodRepository;

    public PeriodServiceImpl(PeriodRepository periodRepository) {
        this.periodRepository = periodRepository;
    }

    @Override
    public Period findById(Long id) {
        return periodRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public Collection<Period> findAll() {
        return periodRepository.findAll();
    }

    @Override
    public Period add(Period period) {
        return periodRepository.save(period);
    }

    @Override
    public void update(Period period) {
        periodRepository.save(period);
    }

    @Override
    public void deleteById(Long id) {
        periodRepository.deleteById(id);
    }
}
