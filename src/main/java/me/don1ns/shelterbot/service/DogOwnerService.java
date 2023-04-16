package me.don1ns.shelterbot.service;

import me.don1ns.shelterbot.model.DogOwner;
import me.don1ns.shelterbot.repository.DogOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    //метод получения хозяина по его id
    public Optional<DogOwner> getById(Long id) {
        //todo Трабл с optional
        return repository.findById(id);
    }

    //метод удаления
    public void delete(DogOwner dogOwner) {
        repository.delete(dogOwner);
    }
}
