package com.example.tidsbanken.services.vacation_request;

import com.example.tidsbanken.enumerator.Response;
import com.example.tidsbanken.model.entities.VacationRequest;
import com.example.tidsbanken.model.entities.VacationResponse;
import com.example.tidsbanken.repositories.VacationRequestRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<VacationRequest> findByEmployeeId(Long employeeId) {
        return vacationRequestRepository.findAll()
                .stream()
                .filter(vacationRequest -> vacationRequest.getEmployee().getEmployeeId()==(employeeId))
                .collect(Collectors.toList());
    }
    @Override
    public List<VacationRequest> findByEmployeeIdApprovedOrPending(Long employeeId) {
        return vacationRequestRepository.findAll()
                .stream()
                .filter(vacationRequest -> vacationRequest.getEmployee().getEmployeeId()==(employeeId))
                .filter(vacationRequest -> {
                    VacationResponse response = vacationRequest.getVacationResponse();
                    return response == null || Response.APPROVED.equals(response.getResponse());
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<VacationRequest> findAllApprovedOrPending() {
        return vacationRequestRepository.findAll()
                .stream()

                .filter(vacationRequest -> {
                    VacationResponse response = vacationRequest.getVacationResponse();
                    return response == null || Response.APPROVED.equals(response.getResponse());
                })
                .collect(Collectors.toList());
    }

}
