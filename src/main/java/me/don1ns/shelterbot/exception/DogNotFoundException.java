package me.don1ns.shelterbot.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
Класс исключения для собак
@автор Елена Никитина
*/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DogNotFoundException extends RuntimeException {
    public DogNotFoundException() {
        super("Dog is not found.");
    }
}

