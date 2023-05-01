package me.don1ns.shelterbot.controller;

import liquibase.pro.packaged.D;
import me.don1ns.shelterbot.model.CatOwners;
import me.don1ns.shelterbot.model.DogOwner;
import me.don1ns.shelterbot.service.CatOwnersService;
import me.don1ns.shelterbot.service.DogOwnerService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Класс тестов контролера для владельцев собак
 * @author Серебряков Алексей
 **/
@WebMvcTest(DogOwnerController.class)
class DogOwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DogOwnerService service;

    @Test
    void getById() throws Exception {
        DogOwner dogOwner = new DogOwner();
        dogOwner.setId(1L);
        service.save(dogOwner);
        when(service.getById(anyLong())).thenReturn(dogOwner);
        mockMvc.perform(
                        get("/dog-owners/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
        verify(service).getById(1L);
    }


    @Test
    void getAll() throws Exception {
        when(service.getAll()).thenReturn(List.of(new DogOwner()));
        mockMvc.perform(
                        get("/dog-owners/all"))
                .andExpect(status().isOk());
    }

    @Test
    void save() throws Exception {
        DogOwner dogOwner = new DogOwner();
        dogOwner.setId(1L);
        dogOwner.setName("John");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "John");
        when(service.save(dogOwner)).thenReturn(dogOwner);
        mockMvc.perform(
                        post("/dog-owners")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));
        verify(service).save(dogOwner);
    }

    @Test
    void update() throws Exception {
        DogOwner dogOwner = new DogOwner();
        dogOwner.setId(1L);
        dogOwner.setName("John");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "John");
        when(service.save(dogOwner)).thenReturn(dogOwner);
        mockMvc.perform(
                        post("/dog-owners")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));
        verify(service).save(dogOwner);
    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(
                        delete("/dog-owners/{id}", 1))
                .andExpect(status().isOk());
        verify(service).delete(1L);
    }
}