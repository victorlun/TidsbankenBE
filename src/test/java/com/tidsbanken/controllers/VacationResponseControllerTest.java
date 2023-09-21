package com.tidsbanken.controllers;
import com.tidsbanken.mappers.VacationResponseMapper;
import com.tidsbanken.models.dtos.VacationResponse.VacationResponseDTO;
import com.tidsbanken.models.dtos.VacationResponse.VacationResponsePostDTO;
import com.tidsbanken.models.entities.VacationResponse;
import com.tidsbanken.services.vacation_response.VacationResponseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class VacationResponseControllerTest {

    @InjectMocks
    private VacationResponseController vacationResponseController;

    @Mock
    private VacationResponseService vacationResponseService;

    @Mock
    private VacationResponseMapper vacationResponseMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllReturnsOkStatus_shouldReturnOkStatus() {
        when(vacationResponseService.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<VacationResponseDTO>> responseEntity = vacationResponseController.getAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetAllCallsServiceFindAll_shouldReturnAllService() {
        when(vacationResponseService.findAll()).thenReturn(new ArrayList<>());
        vacationResponseController.getAll();
        verify(vacationResponseService, times(1)).findAll();
    }

    @Test
    void testGetAllReturnsDTOs_shouldReturnAllDTOs() {
        List<VacationResponse> vacationResponses = new ArrayList<>();
        List<VacationResponseDTO> vacationResponseDTOs = new ArrayList<>();
        vacationResponses.add(new VacationResponse());
        vacationResponses.add(new VacationResponse());

        for (VacationResponse vacationResponse : vacationResponses) {
            VacationResponseDTO dto = new VacationResponseDTO();
            when(vacationResponseMapper.vacationResponseToDTO(vacationResponse)).thenReturn(dto);
            vacationResponseDTOs.add(dto);
        }
        when(vacationResponseService.findAll()).thenReturn(vacationResponses);
        ResponseEntity<List<VacationResponseDTO>> responseEntity = vacationResponseController.getAll();
        assertEquals(vacationResponseDTOs, responseEntity.getBody());
    }

    @Test
    void testGetByIdReturnsOkStatus_shouldReturnOkResponse() {
        when(vacationResponseService.findById(anyLong())).thenReturn((new VacationResponse()));
        when(vacationResponseMapper.vacationResponseToDTO(any())).thenReturn(new VacationResponseDTO());
        ResponseEntity<VacationResponseDTO> responseEntity = vacationResponseController.getById(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetByIdCallsServiceFindById_shouldReturnResponseId() {
        when(vacationResponseService.findById(anyLong())).thenReturn((new VacationResponse()));
        when(vacationResponseMapper.vacationResponseToDTO(any())).thenReturn(new VacationResponseDTO());
        vacationResponseController.getById(1L);
        verify(vacationResponseService, times(1)).findById(1L);
    }

    @Test
    void testGetById_shouldReturnDto() {
        VacationResponse vacationResponse = new VacationResponse();
        VacationResponseDTO vacationResponseDTO = new VacationResponseDTO();
        when(vacationResponseService.findById(anyLong())).thenReturn((vacationResponse));
        when(vacationResponseMapper.vacationResponseToDTO(vacationResponse)).thenReturn(vacationResponseDTO);
        ResponseEntity<VacationResponseDTO> responseEntity = vacationResponseController.getById(1L);
        assertEquals(vacationResponseDTO, responseEntity.getBody());
    }
    @Test
    void testCreateResponseSuccess_shouldReturnResponseSuccessWhenCreated() {
        VacationResponsePostDTO postDTO = new VacationResponsePostDTO();
        VacationResponse vacationResponse = new VacationResponse();
        when(vacationResponseMapper.PostDTOToVacationResponse(postDTO)).thenReturn(vacationResponse);
        when(vacationResponseService.add(vacationResponse)).thenReturn(vacationResponse);
        ResponseEntity<VacationResponse> responseEntity = vacationResponseController.createResponse(postDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(vacationResponseMapper, times(1)).PostDTOToVacationResponse(postDTO);
        verify(vacationResponseService, times(1)).add(vacationResponse);
    }

    @Test
    void testCreateResponseInternalServerError_shouldReturnErrorWhenCreatingNewResponse() {
        VacationResponsePostDTO postDTO = new VacationResponsePostDTO();
        VacationResponse vacationResponse = new VacationResponse();
        when(vacationResponseMapper.PostDTOToVacationResponse(postDTO)).thenReturn(vacationResponse);
        when(vacationResponseService.add(vacationResponse)).thenThrow(new RuntimeException("Error adding response"));
        ResponseEntity<VacationResponse> responseEntity = vacationResponseController.createResponse(postDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        verify(vacationResponseMapper, times(1)).PostDTOToVacationResponse(postDTO);
        verify(vacationResponseService, times(1)).add(vacationResponse);
    }
    @Test
    void testUpdateResponseSuccess_shouldReturnNewUpdatedResponse() {
        Long responseId = 1L;
        VacationResponse newVacationResponse = new VacationResponse();
        VacationResponse oldVacationResponse = new VacationResponse();
        when(vacationResponseService.findById(responseId)).thenReturn(oldVacationResponse);
        ResponseEntity<Void> responseEntity = vacationResponseController.updateResponse(responseId, newVacationResponse);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(vacationResponseService, times(1)).findById(responseId);
        verify(vacationResponseService, times(1)).update(oldVacationResponse);
        assertEquals(newVacationResponse.getVacationResponseId(), oldVacationResponse.getVacationResponseId());
        assertEquals(newVacationResponse.getResponse(), oldVacationResponse.getResponse());
        assertEquals(newVacationResponse.getResponseComment(), oldVacationResponse.getResponseComment());
        assertEquals(newVacationResponse.getVacationRequest(), oldVacationResponse.getVacationRequest());
    }

    @Test
    void testUpdateResponseNotFound_shouldReturnResponseNotFound() {
        Long responseId = 1L;
        VacationResponse newVacationResponse = new VacationResponse();
        when(vacationResponseService.findById(responseId)).thenReturn(null);
        ResponseEntity<Void> responseEntity = vacationResponseController.updateResponse(responseId, newVacationResponse);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(vacationResponseService, times(1)).findById(responseId);
        verify(vacationResponseService, never()).update(any());
    }
    @Test
    void testDeleteResponseSuccess_shouldDeleteResponse() {
        Long responseId = 1L;
        ResponseEntity<Void> responseEntity = vacationResponseController.deleteResponse(responseId);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(vacationResponseService, times(1)).deleteById(responseId);
    }

    @Test
    void testDeleteResponseError_shouldReturnErrorIfNotDelete() {
        Long responseId = 1L;
        doThrow(new RuntimeException("Delete error")).when(vacationResponseService).deleteById(responseId);
        ResponseEntity<Void> responseEntity = vacationResponseController.deleteResponse(responseId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        verify(vacationResponseService, times(1)).deleteById(responseId);
    }



}

