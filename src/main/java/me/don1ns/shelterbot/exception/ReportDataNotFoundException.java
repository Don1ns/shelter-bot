package me.don1ns.shelterbot.exception;
/**
* Класс Ошибки отчетов
* @author Королёв Артем
 **/
public class ReportDataNotFoundException extends RuntimeException {
    public ReportDataNotFoundException(String message) {
        super(message);
    }
}
