package me.don1ns.shelterbot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Класс модель котов
 * @author Герасименко Максим
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat {

    /**
     * Поле идентификатор
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * Поле "имя кота"
     */
    private String name;
    /**
     * Поле "порода кота"
     */
    private String breed;
    /**
     * Поле "год рождения кота"
     */
    private int yearOfBirth;
    /**
     * Поле "описание кота" или доп. информация о питомце
     */
    private String description;


    @Override
    public String toString() {
        return "Кличка кота - " + getName() + ", год рождения - " + getYearOfBirth() +
                ", порода - " + getBreed() + ", дополнительная информация о коте - " + description;
    }

}
