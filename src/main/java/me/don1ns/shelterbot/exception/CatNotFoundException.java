package me.don1ns.shelterbot.exception;
/*Класс исключения для котов
* @автор Герасименко Максим*/
public class CatNotFoundException extends RuntimeException{
    public CatNotFoundException() {
        super("Cat is not found.");
    }
}
