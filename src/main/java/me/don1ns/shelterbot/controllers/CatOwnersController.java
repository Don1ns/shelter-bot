package me.don1ns.shelterbot.controllers;
import io.swagger.v3.oas.annotations.Operation;
import me.don1ns.shelterbot.model.CatOwners;
import me.don1ns.shelterbot.service.CatOwnersService;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
/*
Класс Контролер для владельцев котиков
@автор Королёв Артем
 */
@RestController
@RequestMapping("owners-cat")
public class CatOwnersController {
    private final CatOwnersService service;
    public CatOwnersController(CatOwnersService service) {
        this.service = service;
    }
    @Operation(summary = "Получение пользователя по id")
    @GetMapping("/{id}")
    public CatOwners getById(@PathVariable Long id) {
        return this.service.getById(id);
    }
    @Operation(summary = "Создание пользователя")
    @PostMapping()
    public CatOwners save(@RequestBody CatOwners catOwners) {
        return this.service.create(catOwners);
    }
    @Operation(summary = "Изменение данных пользователя")
    @PutMapping
    public CatOwners update(@RequestBody CatOwners catOwners) {
        return this.service.update(catOwners);
    }
    @Operation(summary = "Удаление пользователей по id")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        this.service.removeById(id);
    }
    @Operation(summary = "Просмотр всех пользователей",
            description = "Просмотр всех пользователей, либо определенного пользователя по chat_id")
    @GetMapping("/all")
    public Collection<CatOwners> getAll(@RequestParam(required = false) Long chatId) {
        if (chatId != null) {
            return this.service.getByChatId(chatId);
        }
        return this.service.getAll();
    }
    //Дописать комменты к методам
}
