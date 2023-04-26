package me.don1ns.shelterbot.repository;
import me.don1ns.shelterbot.model.ReportData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface ReportDataRepository extends JpaRepository<ReportData, Long> {
    Set<ReportData> findListByChatId(Long id);
    ReportData findByChatId(Long id);
}
