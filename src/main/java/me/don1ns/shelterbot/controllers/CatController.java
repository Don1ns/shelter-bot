package me.don1ns.shelterbot.controllers;

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

        private final CatService service;
    public CatController(CatService service) {
        this.service = service;
    }
    @PostMapping
        public Cat AddCat(@RequestBody Cat cat)
        {
            Cat obj = dogService.AddDog(dog);
            return obj;
        }

        @GetMapping("{id}")
        public Dog getDog(@PathVariable int id)
        {
            Dog obj = dogService.getDog(id);
            return obj;
        }

        @PutMapping("{id}")
        public Dog editDog(@PathVariable int id, @RequestBody Dog dog)
        {
            Dog obj = dogService.editDog(id, dog);
            return obj;
        }

        @DeleteMapping("{id}")
        public Dog deleteDog(@PathVariable int id)
        {
            Dog obj = dogService.deleteDog(id);
            return obj;
        }
}
