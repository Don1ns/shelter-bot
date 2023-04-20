package me.don1ns.shelterbot.service;

import me.don1ns.shelterbot.exception.DogOwnerNotFoundException;
import me.don1ns.shelterbot.model.DogOwner;
import me.don1ns.shelterbot.repository.DogOwnerRepository;
import org.springframework.stereotype.Service;

/**
 * Класс сервис для DogOwner
 **/

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
    public DogOwner getById(Long id) {
        return repository.findById(id).orElseThrow(DogOwnerNotFoundException::new);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public DogOwner getByChatId(Long chatId) {
        return repository.getDogOwnerByChatId(chatId);
    }
}
