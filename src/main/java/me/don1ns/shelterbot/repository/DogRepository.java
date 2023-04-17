package me.don1ns.shelterbot.repository;
import me.don1ns.shelterbot.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

/*
Класс репозиторий собак
@автор Елена Никитина
 */
@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
    Set<Dog> findByChatId(Long chatId);
}