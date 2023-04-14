package me.don1ns.shelterbot.Exeption;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
/*
Класс Ошибки не найденых владельцев котиков
@автор Королёв Артем
 */
//Этот класс используется для создания исключения в случае, если указанный CatOwner не найден.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CatOwnersNotFoundException extends RuntimeException{
    public CatOwnersNotFoundException() {
        super("CatOwner is not found.");
    }
}
