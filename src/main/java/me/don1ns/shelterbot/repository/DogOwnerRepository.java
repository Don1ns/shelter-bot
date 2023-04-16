package me.don1ns.shelterbot.repository;

import me.don1ns.shelterbot.entity.DogOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogOwnerRepository extends JpaRepository<DogOwner, Long> {
}
