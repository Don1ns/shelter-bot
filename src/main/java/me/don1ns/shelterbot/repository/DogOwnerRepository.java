package me.don1ns.shelterbot.repository;

import me.don1ns.shelterbot.model.DogOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DogOwnerRepository extends JpaRepository<DogOwner, Long> {
    Set<DogOwner> getDogOwnerByChatId(Long chatId);
}
