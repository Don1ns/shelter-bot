package me.don1ns.shelterbot.repository;

import me.don1ns.shelterbot.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

/*
Класс Репозиторий котиков
@автор Герасименко Максим
 */
public interface CatRepository extends JpaRepository<Cat, Long> {

    //TODO написать логику

}
