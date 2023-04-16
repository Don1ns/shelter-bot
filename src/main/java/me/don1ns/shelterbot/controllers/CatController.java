package me.don1ns.shelterbot.controllers;

import io.swagger.v3.oas.annotations.Operation;
import me.don1ns.shelterbot.model.Cat;
import me.don1ns.shelterbot.service.CatService;
import org.springframework.web.bind.annotation.*;

/*
Класс Контролер для котиков
@автор Герасименко Максим
 */
@RestController
@RequestMapping("cat")
public class CatController {

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @Operation(summary = "Создание кота")
    @PostMapping
    public Cat AddCat(@RequestBody Cat cat) {
        return catService.AddCat(cat);
    }


    @Operation(summary = "Получение кота по id")
    @GetMapping("/{id}")
    public Cat getCatById(@PathVariable Long id) {
        return catService.getCatByID(id);
    }

    @Operation(summary = "Обновление данных кота")
    @PutMapping("/{id}")
    public Cat updateCatById(@PathVariable Long id, @RequestBody Cat cat) {
        return catService.editCat(id, cat);
    }

    @Operation(summary = "Удаление кота по id")
    @DeleteMapping("/{id}")
    public Cat removeCat(@PathVariable Long id) {
        return catService.removeCat(id);
    }
}