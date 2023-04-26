package me.don1ns.shelterbot.service;
import me.don1ns.shelterbot.exception.ReportDataNotFoundException;
import me.don1ns.shelterbot.model.ReportData;
import me.don1ns.shelterbot.repository.ReportDataRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
@Service
@Transactional
public class ReportDataService {
    private final ReportDataRepository repository;
    public ReportDataService(ReportDataRepository reportRepository) {
        this.repository = reportRepository;
    }
    public void uploadReportData(Long chatId,String name, byte[] pictureFile,
                                 String ration, String health, String behaviour,
                                 String filePath) throws IOException {
        ReportData report = new ReportData();
        report.setChatId(chatId);
        report.setName(name);
        report.setData(pictureFile);
        report.setRation(ration);
        report.setHealth(health);
        report.setBehaviour(behaviour);
        report.setFilePath(filePath);
        this.repository.save(report);
    }
    public ReportData findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(()->new ReportDataNotFoundException("Data not found exceptions"));
    }
    public ReportData findByChatId(Long chatId) {
        return this.repository.findByChatId(chatId);
    }
    public Collection<ReportData> findListByChatId(Long chatId) {
        return this.repository.findListByChatId(chatId);
    }
    public ReportData save(ReportData report) {
        return this.repository.save(report);
    }
    public void remove(Long id) {
        this.repository.deleteById(id);
    }
    public List<ReportData> getAll() {
        return this.repository.findAll();
    }

}
