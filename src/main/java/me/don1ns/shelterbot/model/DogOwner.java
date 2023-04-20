package me.don1ns.shelterbot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
/**
 * Класс Владельцев собак
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dog_owners")
public class DogOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //name пользователя
    @Column(name = "name")
    private String name;
    //yearOfBirth год рождения пользователя
    @Column(name = "yearOfBirth")
    private int yearOfBirth;
    //phone телефон пользователя
    @Column(name = "phone")
    private String phone;
    //mail електроная почта пользователя
    @Column(name = "email")
    private String mail;
    //address пользователя
    @Column(name = "address")
    private String address;

    //chat id пользователя
    @Column(name = "chat_id")
    private Long chatId;

    //связь OneToOne с собакой
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id", referencedColumnName = "id")
    private Dog dog;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DogOwner dogOwner = (DogOwner) o;
        return Objects.equals(id, dogOwner.id) && Objects.equals(name, dogOwner.name) && Objects.equals(chatId, dogOwner.chatId) && Objects.equals(dog, dogOwner.dog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, chatId, dog);
    }

    @Override
    public String toString() {
        return "DogOwner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chatId='" + chatId + '\'' +
                ", dog=" + dog +
                '}';
    }
}
