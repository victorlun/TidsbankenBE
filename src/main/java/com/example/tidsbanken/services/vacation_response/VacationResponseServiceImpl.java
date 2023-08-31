package com.example.tidsbanken.services.vacation_response;

import com.example.tidsbanken.model.entities.VacationResponse;
import com.example.tidsbanken.repositories.VacationResponseRepository;

import java.util.Collection;

public class VacationResponseServiceImpl implements VacationResponseService {
    private final VacationResponseRepository vacationResponseRepository;

    public VacationResponseServiceImpl(VacationResponseRepository vacationResponseRepository) {
        this.vacationResponseRepository = vacationResponseRepository;
    }

    @Override
    public VacationResponse findById(Long id) {
        return vacationResponseRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public Collection<VacationResponse> findAll() {
        return vacationResponseRepository.findAll();
    }

    @Override
    public VacationResponse add(VacationResponse vacationResponse) {
        return vacationResponseRepository.save(vacationResponse);
    }

    @Override
    public void update(VacationResponse vacationResponse) {
        vacationResponseRepository.save(vacationResponse);
    }

    @Override
    public void deleteById(Long id) {
        vacationResponseRepository.deleteById(id);

    }
}
