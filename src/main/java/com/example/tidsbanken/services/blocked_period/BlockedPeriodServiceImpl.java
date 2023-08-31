package com.example.tidsbanken.services.blocked_period;

import com.example.tidsbanken.model.entities.BlockedPeriod;
import com.example.tidsbanken.model.entities.Period;
import com.example.tidsbanken.repositories.BlockedPeriodRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BlockedPeriodServiceImpl implements BlockedPeriodService {
    private final BlockedPeriodRepository blockedPeriodRepository;

    public BlockedPeriodServiceImpl(BlockedPeriodRepository blockedPeriodRepository) {
        this.blockedPeriodRepository = blockedPeriodRepository;
    }

    @Override
    public BlockedPeriod findById(Period period) {
        return null;
    }

    @Override
    public Collection<BlockedPeriod> findAll() {
        return blockedPeriodRepository.findAll();
    }

    @Override
    public BlockedPeriod add(BlockedPeriod blockedPeriod) {
        return blockedPeriodRepository.save(blockedPeriod);
    }

    @Override
    public void update(BlockedPeriod blockedPeriod) {
        blockedPeriodRepository.save(blockedPeriod);
    }

    @Override
    public void deleteById(Period period) {

    }
}
