package me.don1ns.shelterbot.controller;
import me.don1ns.shelterbot.model.Dog;
import me.don1ns.shelterbot.service.DogService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Класс тестов контролера для собак
 *
 * @author Елена Никитина
 **/
@WebMvcTest(DogController.class)
class DogControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DogService service;

    @Test
    void getById() throws Exception {
        Dog dog = new Dog();
        dog.setId(1L);
        when(service.getById(anyLong())).thenReturn(dog);
        mockMvc.perform(
                        get("/dog/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
        verify(service).getById(1L);
    }

    @Test
    void save() throws Exception {
        Dog dog = new Dog();
        dog.setId(1L);
        dog.setName("testDog");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "testDog");
        userObject.put("breed", "testBreed");
        userObject.put("yearOfBirth", 2020);
        userObject.put("description", "test");
        when(service.addDog(dog)).thenReturn(dog);
        mockMvc.perform(
                        post("/dog")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));
        verify(service).addDog(dog);
    }

    @Test
    void update() throws Exception {
        Dog dog = new Dog();
        dog.setId(1L);
        dog.setName("testDog");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "testDog");
        when(service.update(dog)).thenReturn(dog);
        mockMvc.perform(
                        put("/dog")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));
        verify(service).update(dog);
    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(
                        delete("dog/{id}", 1))
                .andExpect(status().isOk());
        verify(service).removeById(1L);
    }
}