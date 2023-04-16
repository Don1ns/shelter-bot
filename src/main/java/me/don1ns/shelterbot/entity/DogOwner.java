package me.don1ns.shelterbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

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

    @Column(name = "name")
    private String name;

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "dog_id")
    private Long dogId;

    //связь OneToOne с собакой
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id", referencedColumnName = "id")
    private Dog dog;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DogOwner dogOwner = (DogOwner) o;
        return Objects.equals(id, dogOwner.id) && Objects.equals(name, dogOwner.name) && Objects.equals(chatId, dogOwner.chatId) && Objects.equals(dogId, dogOwner.dogId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, chatId, dogId);
    }

    @Override
    public String toString() {
        return "DogOwner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chatId='" + chatId + '\'' +
                ", dogId=" + dogId +
                '}';
    }
}
