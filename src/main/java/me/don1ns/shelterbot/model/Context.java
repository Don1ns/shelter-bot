package me.don1ns.shelterbot.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import me.don1ns.shelterbot.constant.ShelterType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
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
    @Enumerated
    private ShelterType shelterType;
    @OneToOne
    private CatOwners catOwner;
    @OneToOne
    private DogOwner dogOwner;

    public Context(Long chatId, ShelterType shelterType) {
        this.chatId = chatId;
        this.shelterType = shelterType;
    }
}
