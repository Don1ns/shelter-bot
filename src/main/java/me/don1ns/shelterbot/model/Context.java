package me.don1ns.shelterbot.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Класс, необходимый для отслеживания состояния пользователя
 *
 * @author Riyaz Karimullin
 */
@Entity
@Data
@NoArgsConstructor
public class Context {
    @Id
    private Long chatId;
    private String shelterType;
    @OneToOne
    CatOwners catOwner;
    @OneToOne
    DogOwner dogOwner;

    public Context(Long chatId, String shelterType) {
        this.chatId = chatId;
        this.shelterType = shelterType;
    }
}
