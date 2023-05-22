package me.don1ns.shelterbot.controller;

import me.don1ns.shelterbot.model.DogOwner;
import me.don1ns.shelterbot.model.ReportData;
import me.don1ns.shelterbot.service.ReportDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Класс тестов контролера для репортов
 *
 * @author Серебряков Алексей
 **/
@WebMvcTest(ReportDataController.class)
class ReportDataControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReportDataService service;

    @Test
    void downloadReport() throws Exception {
        ReportData reportData = new ReportData();
        reportData.setId(1L);
        service.save(reportData);
        when(service.findById(anyLong())).thenReturn(reportData);
        mockMvc.perform(
                        get("/photo-reports/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
        verify(service).findById(1L);
    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(
                        delete("/photo-reports/{id}", 1))
                .andExpect(status().isOk());
        verify(service).remove(1L);
    }

    @Test
    void getAll() throws Exception {
        when(service.getAll()).thenReturn(List.of(new ReportData()));
        mockMvc.perform(
                        get("/photo-reports/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void downloadPhotoFromDB() throws Exception {
    }
}