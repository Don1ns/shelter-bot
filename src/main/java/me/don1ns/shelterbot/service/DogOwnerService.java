package me.don1ns.shelterbot.service;

import me.don1ns.shelterbot.entity.DogOwner;
import me.don1ns.shelterbot.repository.DogOwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class DogOwnerService {
    private final DogOwnerRepository repository;

    public DogOwnerService(DogOwnerRepository repository) {
        this.repository = repository;
    }

    //метод работает на сохранение и обновление
    public void save(DogOwner dogOwner) {
        repository.save(dogOwner);
    }

    //метод позволяет получить DogOwner по id
    public void getById(Long id) {
        repository.findById(id);
    }

    public void delete(DogOwner dogOwner) {
        repository.delete(dogOwner);
    }
}
