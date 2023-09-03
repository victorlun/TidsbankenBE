package com.example.tidsbanken.services.vacation_request;

import com.example.tidsbanken.model.VacationRequest;
import com.example.tidsbanken.repositories.VacationRequestRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class VacationRequestServiceImpl implements VacationRequestService{
    private final VacationRequestRepository vacationRequestRepository;

    public VacationRequestServiceImpl(VacationRequestRepository vacationRequestRepository) {
        this.vacationRequestRepository = vacationRequestRepository;
    }

    @Override
    public VacationRequest findById(Long id) {
        return vacationRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public Collection<VacationRequest> findAll() {
        return vacationRequestRepository.findAll();
    }

    @Override
    public VacationRequest add(VacationRequest vacationRequest) {
        return vacationRequestRepository.save(vacationRequest);
    }

    @Override
    public void update(VacationRequest vacationRequest) {
        vacationRequestRepository.save(vacationRequest);
    }

    @Override
    public void deleteById(Long id) {
        vacationRequestRepository.deleteById(id);
    }
}
