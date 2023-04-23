package me.don1ns.shelterbot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
Класс котиков
@автор Максим Герасименко
*/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat {
    // ID кота
    @Id
    @GeneratedValue
    private Long id;
    // Имя кота
    private String name;
    // Порода кота
    private String breed;
    // Год рождения кота
    private int yearOfBirth;
    // Описание кота
    private String description;


    @Override
    public String toString() {
        return "Кличка кота - " + getName() + ", год рождения - " + getYearOfBirth() +
                ", порода - " + getBreed() + ", дополнительная информация о коте - " + description;
    }

}
