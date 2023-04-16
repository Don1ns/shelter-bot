package me.don1ns.shelterbot.service;

import me.don1ns.shelterbot.exception.CatNotFoundException;
import me.don1ns.shelterbot.model.Cat;
import me.don1ns.shelterbot.repository.CatRepository;
import org.springframework.stereotype.Service;

/*
 * CRUD операции для котов
 * @автор Герасименко Максим
 */
@Service
public class CatService {
    private final CatRepository repository;

    public CatService(CatRepository repository) {
        this.repository = repository;
    }

    /*
     Добавление нового кота в список
      */
    public Cat addCat(Cat cat) {
        return this.repository.save(cat);
    }

    /*
    получение кота по ID
    */
    public Cat getById(Long id) {
        return this.repository.findById(id).orElseThrow(CatNotFoundException::new);
    }
    /*
     * Обновление кота по ID
     */

    public Cat update(Cat cat) {

        if (cat.getId() != null) {
            if (getById(cat.getId()) != null) {
                return repository.save(cat);
            }
        }
        throw new CatNotFoundException();
    }
    /*
     * Удаление кота из списка по ID
     */

    public Cat removeById(Long id) {
        this.repository.deleteById(id);
        return null;
    }
}
