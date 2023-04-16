package me.don1ns.shelterbot.controller;

import me.don1ns.shelterbot.service.DogOwnerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DogOwner")
public class DogOwnerController {
    private final DogOwnerService dogOwnerService;


    public DogOwnerController(DogOwnerService dogOwnerService) {
        this.dogOwnerService = dogOwnerService;
    }


}
