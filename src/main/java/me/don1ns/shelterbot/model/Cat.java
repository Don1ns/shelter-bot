package me.don1ns.shelterbot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
Класс котиков
@автор Максим Герасименко
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat {
    // ID кота
    private Long id;
    //Имя кота
    private String name;
    //Порода кота
    private String breed;
    // Год рождения кота
    private int yearOfBirth;
    //описание кота
    private String description;


    @Override
    public String toString() {
        return "Кличка кота - " + getName() + ", год рождения - " + getYearOfBirth() +
                ", порода - " + getBreed() + ", дополнительная информация о коте - " + description;
    }
}
