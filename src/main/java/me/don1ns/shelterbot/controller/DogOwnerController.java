package me.don1ns.shelterbot.controller;

import io.swagger.v3.oas.annotations.Operation;
import me.don1ns.shelterbot.model.DogOwner;
import me.don1ns.shelterbot.service.DogOwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
* Класс Контролер для владельцев собак
 */
@RestController
@RequestMapping("/DogOwner")
public class DogOwnerController {
    private final DogOwnerService dogOwnerService;

    public DogOwnerController(DogOwnerService dogOwnerService) {
        this.dogOwnerService = dogOwnerService;
    }

    @Operation(summary = "Получение пользователя по id")
    @GetMapping("/{id}")
    public DogOwner getById(@PathVariable Long id) {
        return dogOwnerService.getById(id);
    }

    @Operation(summary = "Получение пользователя по chat id")
    @GetMapping("/{chatId}")
    public Set<DogOwner> getByChatId(@PathVariable Long chatId) {
        return dogOwnerService.getByChatId(chatId);
    }
    @Operation(summary = "Создание пользователя")
    @PostMapping()
    public ResponseEntity<Void> save(@RequestBody DogOwner dogOwner) {
        dogOwnerService.save(dogOwner);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Изменение данных пользователя")
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody DogOwner dogOwner) {
        dogOwnerService.save(dogOwner);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Удаление пользователей по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        dogOwnerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
