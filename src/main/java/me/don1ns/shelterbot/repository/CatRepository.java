package me.don1ns.shelterbot.repository;

import me.don1ns.shelterbot.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Класс репозиторий котов
 * @author Герасименко Максим
 */
public interface CatRepository extends JpaRepository<Cat, Long> {
}
