package me.don1ns.shelterbot.repository;
import me.don1ns.shelterbot.model.CatOwners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;
/*
Класс Репозиторий владельцев котиков
@автор Королёв Артем
 */
//Этот репозиторий предоставляет уровень доступа к данным для управления сущностями CatOwners в базе данных.
//Он расширяет JpaRepository и предоставляет доступ к набору владельцев кошек с заданным идентификатором чата.
@Repository
public interface CatOwnersRepository extends JpaRepository<CatOwners, Long> {
    Set<CatOwners> findByChatId(Long chatId);
}
