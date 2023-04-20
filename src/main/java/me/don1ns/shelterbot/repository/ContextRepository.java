package me.don1ns.shelterbot.repository;

import me.don1ns.shelterbot.model.Context;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий класса Context
 * Предоставляет доступ к данным для управления сущностями Context в базе данных
 *
 * @author Riyaz Karimullin
 */
@Repository
public interface ContextRepository extends JpaRepository<Context, Long> {
    Optional<Context> findByChatId(Long chatId);
}
