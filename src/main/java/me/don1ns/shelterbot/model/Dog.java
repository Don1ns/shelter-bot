package me.don1ns.shelterbot.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/*
Класс собаки
@автор Елена Никитина
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dog {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String breed;
    private int yearOfBirth;
    private String description;

    @Override
    public String toString() {
        return "Кличка собаки - " + getName() + ", год рождения - " + getYearOfBirth() +
                ", порода - " + getBreed();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Dog == false || obj == null)
            return false;

        Dog dog = (Dog)obj;

        if (getId() == (dog.getId()) && getName().equals(dog.getName()))
            return true;

        return false;
    }

    @Override
    public int hashCode()
    {
        return id.hashCode() + name.hashCode();
    }
}