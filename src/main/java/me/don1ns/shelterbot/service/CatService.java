package me.don1ns.shelterbot.service;

import me.don1ns.shelterbot.model.Cat;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/*
 * CRUD операции для котов
 * @автор Герасименко Максим
 */
@Service
public class CatService {
    private final Map<Long, Cat> cats = new HashMap<Long, Cat>();
    private Long id;

    /*
    Добавление нового кота в список
     */
    public Cat AddCat(Cat cat) {
        id++;
        cats.put(id, cat);
        return cat;
    }
    /*
     * Получение кота по ID
     */

    public Cat getCatByID(Long id) {
        return cats.get(id);
    }
    /*
     * Обновление кота по ID
     */

    public Cat editCat(Long id, Cat cat) {
        if (cats.containsKey(id)) {
            Cat item = cats.get(id);
            item.setName(cat.getName());
            item.setBreed(cat.getBreed());
            item.setYearOfBirth(cat.getYearOfBirth());
            item.setDescription(cat.getDescription());
            return item;
        }

        return null;
    }
    /*
     * Удаление кота из списка по ID
     */

    public Cat deleteCat(Long id) {
        if (cats.containsKey(id)) {
            return cats.remove(id);
        }

        return null;
    }
}
