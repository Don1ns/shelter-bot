package me.don1ns.shelterbot.exception;

/*
Класс Ошибки не найденых владельцев собак
 */
public class DogOwnerNotFoundException extends RuntimeException{
    public DogOwnerNotFoundException() {
        super("Dog owner not found!");
    }
}
