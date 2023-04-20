package me.don1ns.shelterbot.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;
/**
* Класс Владельцев котиков
* @author Королёв Артем
 **/
@Data
@Entity
public class CatOwners {
    @Id
    @GeneratedValue
    //id пользователя
    private Long id;
    //name пользователя
    private String name;
    //yearOfBirth год рождения пользователя
    private int yearOfBirth;
    //phone телефон пользователя
    private String phone;
    //mail електроная почта пользователя
    private String mail;
    //adвress пользователя
    private String address;
    //chatId номер чата пользователя с ботом
    private Long chatId;
    //status пользователя
    @Enumerated(EnumType.STRING)
    private Status status;
    //Связь пользователя с класом Cat по cat_id
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id")
    private Cat cat;
    //Пустой конструктор класса
    public CatOwners() {
    }
    //конструктор класса с полями name, phone, chatId.
    public CatOwners(String name, String phone, Long chatId) {
        this.name = name;
        this.phone = phone;
        this.chatId = chatId;
    }
    //Конструктор класса со всеми полями.
    public CatOwners(Long id, String name, int yearOfBirth, String phone, String mail, String address, Long chatId) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.chatId = chatId;
    }
}
