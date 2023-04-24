package me.don1ns.shelterbot.model;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

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
    private String filePath; // TODO нужен для получения пути файла с базы
    @Lob
    private byte[] data; //TODO загружаемый фаил
}
