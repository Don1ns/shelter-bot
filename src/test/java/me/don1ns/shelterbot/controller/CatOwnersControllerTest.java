package me.don1ns.shelterbot.controller;

import me.don1ns.shelterbot.model.CatOwners;
import me.don1ns.shelterbot.service.CatOwnersService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Класс тестов контролера для владельцев котиков
 * @author Королёв Артем
 **/
@WebMvcTest(CatOwnersController.class)
public class CatOwnersControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CatOwnersService service;
    @Test
    void getById() throws Exception {
        CatOwners catOwners = new CatOwners();
        catOwners.setId(1L);
        when(service.getById(anyLong())).thenReturn(catOwners);
        mockMvc.perform(
                        get("/owners-cat/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
        verify(service).getById(1L);
    }
    @Test
    void save() throws Exception {
        CatOwners catOwners = new CatOwners();
        catOwners.setId(1L);
        catOwners.setName("John");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "John");
        when(service.create(catOwners)).thenReturn(catOwners);
        mockMvc.perform(
                        post("/owners-cat")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));
        verify(service).create(catOwners);
    }
    @Test
    void update() throws Exception {
        CatOwners catOwners = new CatOwners();
        catOwners.setId(1L);
        catOwners.setName("John");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "John");
        when(service.update(catOwners)).thenReturn(catOwners);
        mockMvc.perform(
                        put("/owners-cat")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));
        verify(service).update(catOwners);
    }
    @Test
    void remove() throws Exception {
        mockMvc.perform(
                        delete("/owners-cat/{id}", 1))
                .andExpect(status().isOk());
        verify(service).removeById(1L);
    }
    @Test
    void getAll() throws Exception {
        when(service.getAll()).thenReturn(List.of(new CatOwners()));
        mockMvc.perform(
                        get("/owners-cat/all"))
                .andExpect(status().isOk());
    }
}
