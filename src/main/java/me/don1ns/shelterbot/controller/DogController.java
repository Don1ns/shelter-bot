package me.don1ns.shelterbot.controller;
import io.swagger.v3.oas.annotations.Operation;
import me.don1ns.shelterbot.model.Dog;
import me.don1ns.shelterbot.service.DogService;
import org.springframework.web.bind.annotation.*;

/*
Класс Контролер для собак
@автор Елена Никитина
 */
@RestController
@RequestMapping("dog")
public class DogController {
    private final DogService dogService;

    public DogController(DogService dogService)
    {
        this.dogService = dogService;
    }

    @Operation(summary = "Получение собаки по id")
    @GetMapping("/{id}")
    public Dog getDogById(@PathVariable Long id) {
        return dogService.getById(id);
    }

    @Operation(summary = "Создание собаки")
    @PostMapping()
    public Dog addDog(@RequestBody Dog dog) {
        return dogService.addDog(dog);
    }

    @Operation(summary = "Обновление данных собаки")
    @PutMapping("/{id}")
    public Dog updateDogById(@PathVariable Long id, @RequestBody Dog dog) {
        return dogService.update(dog);
    }

    @Operation(summary = "Удаление собаки по id")
    @DeleteMapping("/{id}")
    public void removeDog(@PathVariable Long id) {
        dogService.removeById(id);
    }
}