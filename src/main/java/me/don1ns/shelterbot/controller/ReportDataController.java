package me.don1ns.shelterbot.controller;
import io.swagger.v3.oas.annotations.Parameter;
import me.don1ns.shelterbot.model.ReportData;
import me.don1ns.shelterbot.service.ReportDataService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
@RestController
@RequestMapping("photo-reports")
public class ReportDataController {
    private final ReportDataService reportDataService;
    private final String fileType = "image/jpeg";
    public ReportDataController(ReportDataService reportDataService) {
        this.reportDataService = reportDataService;
    }
    @GetMapping("/{id}/report")
    public ReportData downloadReport(@Parameter(description = "report id") @PathVariable Long id) {
        return this.reportDataService.findById(id);
    }
    @DeleteMapping("/{id}")
    public void remove(@Parameter (description = "report id") @PathVariable Long id) {
        this.reportDataService.remove(id);
    }
    @GetMapping("/getAll")
    public ResponseEntity<Collection<ReportData>> getAll() {
        return ResponseEntity.ok(this.reportDataService.getAll());
    }
    @GetMapping("/{id}/photo-from-db")
    public ResponseEntity<byte[]> downloadPhotoFromDB(@Parameter (description = "report id") @PathVariable Long id) {
        ReportData reportData = this.reportDataService.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fileType));
        headers.setContentLength(reportData.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(reportData.getData());
    }
}
