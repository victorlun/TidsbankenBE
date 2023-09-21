package com.tidsbanken.controllers;

import com.tidsbanken.mappers.BlockedPeriodMapper;
import com.tidsbanken.models.dtos.BlockedPeriod.BlockedPeriodDTO;
import com.tidsbanken.models.dtos.BlockedPeriod.BlockedPeriodPostDTO;
import com.tidsbanken.models.dtos.BlockedPeriod.BlockedPeriodUpdateDTO;
import com.tidsbanken.models.entities.BlockedPeriod;
import com.tidsbanken.models.entities.Employee;
import com.tidsbanken.services.blocked_period.BlockedPeriodService;
import com.tidsbanken.services.employee.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BlockedPeriodControllerTest {
    @InjectMocks
    private BlockedPeriodController blockedPeriodController;
    @Mock
    private BlockedPeriodService blockedPeriodService;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private BlockedPeriodMapper blockedPeriodMapper;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testGetBlockedPeriodById_shouldReturnBlockedPeriodId() {
        Long blockedPeriodId = 1L;
        BlockedPeriod blockedPeriod = new BlockedPeriod();
        BlockedPeriodDTO dto = new BlockedPeriodDTO();
        when(blockedPeriodService.findById(blockedPeriodId)).thenReturn(blockedPeriod);
        when(blockedPeriodMapper.blockedPeriodToBlockedPeriodDto(blockedPeriod)).thenReturn(dto);
        ResponseEntity<?> response = blockedPeriodController.getBlockedPeriodById(blockedPeriodId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }
    @Test
    public void testGetBlockedPeriodByIdNotFound() {
        Long blockedPeriodId = 1L;
        when(blockedPeriodService.findById(blockedPeriodId)).thenReturn(null);
        ResponseEntity<?> response = blockedPeriodController.getBlockedPeriodById(blockedPeriodId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("BlockedPeriod with ID 1 not found.", response.getBody());
    }

    @Test
    public void testGetAllBlockedPeriods_shouldReturnAllBlockedPeriods() throws NoSuchFieldException, IllegalAccessException {
        List<BlockedPeriod> blockedPeriods = new ArrayList<>();
        blockedPeriods.add(new BlockedPeriod());
        blockedPeriods.add(new BlockedPeriod());
        List<BlockedPeriodDTO> dtos = new ArrayList<>();
        dtos.add(new BlockedPeriodDTO());
        dtos.add(new BlockedPeriodDTO());

        when(blockedPeriodService.findAll()).thenReturn(blockedPeriods);
        Field mapperField = BlockedPeriodController.class.getDeclaredField("blockedPeriodMapper");
        mapperField.setAccessible(true);
        BlockedPeriodMapper blockedPeriodMapper = (BlockedPeriodMapper) mapperField.get(blockedPeriodController);
        when(blockedPeriodMapper.blockedPeriodToBlockedPeriodDto(blockedPeriods.get(0))).thenReturn(dtos.get(0));
        when(blockedPeriodMapper.blockedPeriodToBlockedPeriodDto(blockedPeriods.get(1))).thenReturn(dtos.get(1));
        ResponseEntity<Collection<BlockedPeriodDTO>> response = blockedPeriodController.getAllBlockedPeriods();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dtos, response.getBody());
    }

    @Test
    void testCreateBlockedPeriod_ShouldReturnNewBlockedperiod() {
        BlockedPeriodPostDTO postDTO = new BlockedPeriodPostDTO();
        postDTO.setManagerId(1L);
        BlockedPeriod blockedPeriod = new BlockedPeriod();
        BlockedPeriod newBlockedPeriod = new BlockedPeriod();
        when(employeeService.findById(postDTO.getManagerId())).thenReturn(new Employee());
        when(blockedPeriodMapper.blockedPeriodPostDTOToBlockedPeriod(postDTO)).thenReturn(blockedPeriod);
        when(blockedPeriodService.add(blockedPeriod)).thenReturn(newBlockedPeriod);
        ResponseEntity<?> response = blockedPeriodController.createBlockedPeriod(postDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newBlockedPeriod, response.getBody());
    }

    @Test
    void TestUpdateBlockedPeriod_shouldReturnNewBlockedPeriodDate() {
        Long blockedPeriodId = 1L;
        BlockedPeriodUpdateDTO updateDTO = new BlockedPeriodUpdateDTO();
        updateDTO.setManagerId(1L);
        updateDTO.setStartDate(LocalDate.parse("2020-09-20"));
        updateDTO.setEndDate(LocalDate.parse("2020-10-30"));

        BlockedPeriod existingBlockedPeriod = new BlockedPeriod();
        existingBlockedPeriod.setBlockedPeriodId(blockedPeriodId);
        when(blockedPeriodService.findById(blockedPeriodId)).thenReturn(existingBlockedPeriod);
        when(employeeService.findById(updateDTO.getManagerId())).thenReturn(new Employee());
        ResponseEntity<?> response = blockedPeriodController.updateBlockedPeriod(blockedPeriodId, updateDTO);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void TestDeleteBlockedPeriod_shouldDeleteBlockedPeriod() {
        Long blockedPeriodId = 1L;
        BlockedPeriod blockedPeriod = new BlockedPeriod();
        when(blockedPeriodService.findById(blockedPeriodId)).thenReturn(blockedPeriod);
        ResponseEntity<?> response = blockedPeriodController.deleteBlockedPeriod(blockedPeriodId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetByEmployee_shouldreturnEmployee() throws NoSuchFieldException, IllegalAccessException {
        Long managerId = 1L;
        List<BlockedPeriod> blockedPeriods = new ArrayList<>();
        List<BlockedPeriodDTO> dtos = new ArrayList<>();
        when(blockedPeriodService.findByEmployee(managerId)).thenReturn(blockedPeriods);
        BlockedPeriodMapper blockedPeriodMapperMock = mock(BlockedPeriodMapper.class);
        for (BlockedPeriod blockedPeriod : blockedPeriods) {
            BlockedPeriodDTO dto = new BlockedPeriodDTO();
            dtos.add(dto);
            when(blockedPeriodMapperMock.blockedPeriodToBlockedPeriodDto(blockedPeriod)).thenReturn(dto);
        }
        Field mapperField = BlockedPeriodController.class.getDeclaredField("blockedPeriodMapper");
        mapperField.setAccessible(true);
        mapperField.set(blockedPeriodController, blockedPeriodMapperMock);
        ResponseEntity<List<BlockedPeriodDTO>> response = blockedPeriodController.getByEmployee(managerId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dtos, response.getBody());
    }

}