package me.don1ns.shelterbot.model;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;

/**
 * Класс отчетов
 * @author Королёв Артем
 **/
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportData {
    @Id
    @GeneratedValue
    private long id;
    private Long chatId;
    private String name;
    private String ration;
    private String health;
    private String behaviour;
    private Date lastMessage;
    @Lob
    private byte[] data;

    public ReportData(Long chatId, String name, String ration, String health, String behaviour, Date lastMessage, byte[] data) {
        this.chatId = chatId;
        this.name = name;
        this.ration = ration;
        this.health = health;
        this.behaviour = behaviour;
        this.lastMessage = lastMessage;
        this.data = data;
    }
}
