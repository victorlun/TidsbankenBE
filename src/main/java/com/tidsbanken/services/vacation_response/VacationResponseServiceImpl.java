package com.tidsbanken.services.vacation_response;

import com.tidsbanken.models.entities.VacationResponse;
import com.tidsbanken.repositories.VacationResponseRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
@Service
public class VacationResponseServiceImpl implements VacationResponseService {
    private final VacationResponseRepository vacationResponseRepository;
    private final JdbcTemplate jdbcTemplate;
    public VacationResponseServiceImpl(VacationResponseRepository vacationResponseRepository, JdbcTemplate jdbcTemplate) {
        this.vacationResponseRepository = vacationResponseRepository;
        this.jdbcTemplate = jdbcTemplate;
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

    @Transactional
    @Override
    public void deleteById(Long id) {
        VacationResponse vacationResponse = findById(id);  // Assuming this throws an exception if not found
        if (vacationResponse.getVacationRequest() != null) {
            deleteRequestReference(id);
        }
        // Force changes to be written to the database
        vacationResponseRepository.flush();

        // Used JdbcTemplate to execute raw SQL query, had problems with the repository
        String sql = "DELETE FROM vacation_response WHERE vacation_response_id = ?";
        jdbcTemplate.update(sql, id);
    }
    @Override
    public void deleteRequestReference(Long id) {
        VacationResponse vacationResponse = findById(id);
        vacationResponse.setVacationRequest(null);
        vacationResponseRepository.save(vacationResponse);  // Explicitly save the change
        vacationResponseRepository.flush();
    }
}
