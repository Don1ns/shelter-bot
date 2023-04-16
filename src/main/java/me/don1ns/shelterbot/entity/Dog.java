package me.don1ns.shelterbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dogs")
@AllArgsConstructor
@NoArgsConstructor
public class Dog {
    @Id
    private Long id;

    @OneToOne(mappedBy = "dogs", cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    private DogOwner dogOwner;
}
