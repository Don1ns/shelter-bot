package me.don1ns.shelterbot.service;
import me.don1ns.shelterbot.model.Dog;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class DogService {
    //Нужно переписать под репоситорий а не мапу, добавить ошибки в методы (класс ошибки я создал)
    private final Map<Integer, Dog> dogs = new HashMap<Integer, Dog>();
    private int id;

    public Dog AddDog(Dog dog)
    {
        id++;
        dogs.put(id, dog);
        return dog;
    }

    public Dog getDog(int id)
    {
        return dogs.get(id);
    }

    public Dog editDog(int id, Dog dog)
    {
        if (dogs.containsKey(id))
        {
            Dog item = dogs.get(id);
            item.setName(dog.getName());
            item.setBreed(dog.getBreed());
            item.setYearOfBirth(dog.getYearOfBirth());
            item.setDescription(dog.getDescription());
            return item;
        }

        return null;
    }

    public Dog deleteDog(int id)
    {
        if (dogs.containsKey(id))
        {
            Dog dog = dogs.remove(id);
            return dog;
        }

        return null;
    }
}