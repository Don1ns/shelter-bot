package me.don1ns.shelterbot.controller;

import io.swagger.v3.oas.annotations.Operation;
import me.don1ns.shelterbot.model.CatOwners;
import me.don1ns.shelterbot.model.DogOwner;
import me.don1ns.shelterbot.service.DogOwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

/**
* Класс Контролер для владельцев собак
 */
@RestController
@RequestMapping("dog-owners")
public class DogOwnerController {
    private final DogOwnerService service;

    public DogOwnerController(DogOwnerService service) {
        this.service = service;
    }

    @Operation(summary = "Получение пользователя по id")
    @GetMapping("/{id}")
    public DogOwner getById(@PathVariable Long id) {
        return this.service.getById(id);
    }

    @Operation(summary = "Просмотр всех пользователей",
            description = "Просмотр всех пользователей, либо определенного пользователя по chat_id")
    @GetMapping("/all")
    public Collection<DogOwner> getAll(@RequestParam(required = false) Long chatId) {
        if (chatId != null) {
            return service.getByChatId(chatId);
        }
        return service.getAll();
    }
    @Operation(summary = "Создание пользователя")
    @PostMapping()
    public DogOwner save(@RequestBody DogOwner dogOwner) {
        return this.service.save(dogOwner);
    }
    @Operation(summary = "Изменение данных пользователя")
    @PutMapping
    public DogOwner update(@RequestBody DogOwner dogOwner) {
        return this.service.save(dogOwner);
    }
    @Operation(summary = "Удаление пользователей по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
