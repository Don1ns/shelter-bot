package me.don1ns.shelterbot.exception;
/**
 * Класс Ошибки отчетов
 * @author Герасименко Максим
 */
public class CatNotFoundException extends RuntimeException{
    public CatNotFoundException() {
        super("Cat is not found.");
    }
}
