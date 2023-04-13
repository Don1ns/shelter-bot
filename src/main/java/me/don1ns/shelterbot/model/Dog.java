package me.don1ns.shelterbot.model;

public class Dog {
    private int id;
    private String name;
    private String breed;
    private int yearOfBirth;
    private String description;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Кличка собаки - " + getName() + ", год рождения - " + getYearOfBirth() +
                ", порода - " + getBreed();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Dog == false || obj == null)
        {
            return false;
        }

        Dog dog = (Dog)obj;

        if (getId() == (dog.getId()) && getName().equals(dog.getName()))
        {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode()
    {
        return id + name.hashCode();
    }
}