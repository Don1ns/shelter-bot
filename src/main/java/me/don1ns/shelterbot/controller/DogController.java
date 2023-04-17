package me.don1ns.shelterbot.controller;
import me.don1ns.shelterbot.model.Dog;
import me.don1ns.shelterbot.service.DogService;
import org.springframework.web.bind.annotation.*;

@RestController
public class DogController {
    private final DogService dogService;

    public DogController(DogService dogService)
    {
        this.dogService = dogService;
    }

    @PostMapping
    public Dog AddDog(@RequestBody Dog dog)
    {
        Dog obj = dogService.AddDog(dog);
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