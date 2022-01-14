package com.asimkilic.n11.fourthhomework.dbt.controller;


import com.asimkilic.n11.fourthhomework.dbt.dto.DbtDebtBeetweenDatesRequestDto;
import com.asimkilic.n11.fourthhomework.dbt.dto.DbtDebtDto;
import com.asimkilic.n11.fourthhomework.dbt.dto.DbtDebtSaveRequestDto;
import com.asimkilic.n11.fourthhomework.dbt.service.DbtDebtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/debts")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Borç İşlemleri", description = "DbtDebt API")
public class DbtDebtController {

    private final DbtDebtService dbtDebtService;

    @GetMapping
    @Operation(summary = "Bütün kişilerin borçlarını döndürür.")
    public ResponseEntity<List<DbtDebtDto>> getAllDbtDebs() {
        List<DbtDebtDto> allDbtDebt = dbtDebtService.findAllDbtDebt();
        return ResponseEntity.ok(allDbtDebt);
    }


    @GetMapping("/all/user")
    @Operation(summary = "Belirtilen kullanıcının ödenmemiş borçlarını döndürür.")
    public ResponseEntity<List<DbtDebtDto>> getAllUnpaidDbtDebtsByUserId(@Parameter(name = "usrUserId", description = "Borcu sorgulanacak kişi id") String usrUserId) {

        List<DbtDebtDto> allDbtDebt = dbtDebtService.findAllUnpaidDbtDebtByUserId(usrUserId);
        return ResponseEntity.ok(allDbtDebt);
    }

    @GetMapping("/all/user/overdue")
    @Operation(summary = "Belirtilen kullanıcının vadesi geçmiş ödenmemiş borçlarını döndürür.")
    public ResponseEntity<List<DbtDebtDto>> getAllUnpaidOverdueDbtDebtsByUserId(@Parameter(name = "usrUserId", description = "Borcu sorgulanacak kişi id") String usrUserId) {

        List<DbtDebtDto> allDbtDebt = dbtDebtService.findAllUnpaidOverDueDbtDebtByUserId(usrUserId);
        return ResponseEntity.ok(allDbtDebt);
    }

    @GetMapping("/user/total")
    @Operation(summary = "Belirtilen kullanıcının ödenmemiş toplam borç tutarını döner (Gecikme zamları dahil).")
    public ResponseEntity<BigDecimal> getUnpaidTotalDbtDebtsByUserId(@Parameter(name = "usrUserId", description = "Borcu sorgulanacak kişi id") String usrUserId) {
        BigDecimal totalDebt = dbtDebtService.findUnpaidTotalDbtDebtsByUserId(usrUserId);
        return ResponseEntity.ok(totalDebt);
    }
    @GetMapping("/user/total/overdue")
    @Operation(summary = "Belirtilen kullanıcının vadesi geçmiş ödenmemiş borçların toplamını döndürür.")
    public ResponseEntity<BigDecimal> getUnpaidOverdueTotalDbtDebtsByUserId(@Parameter(name = "usrUserId", description = "Borcu sorgulanacak kişi id") String usrUserId) {
        BigDecimal totalDebt = dbtDebtService.findUnpaidOverdueTotalDbtDebtsByUserId(usrUserId);
        return ResponseEntity.ok(totalDebt);
    }
    @GetMapping("/user/total/latefee")
    @Operation(summary = "Belirtilen kullanıcının sorgulama anına ait gecikme zammı toplamını döndürür.")
    public ResponseEntity<BigDecimal> getCurrentLateFeesTotalDbtDebtsByUserId(@Parameter(name = "usrUserId", description = "Borcu sorgulanacak kişi id") String usrUserId) {
        BigDecimal totalDebt = dbtDebtService.findCurrentLateFeesTotalDbtDebtsByUserId(usrUserId);
        return ResponseEntity.ok(totalDebt);
    }

    @PostMapping
    @Operation(summary = "Yeni borç kaydeder, gecikme borcu kaydetmek için kullanılmaz")
    public ResponseEntity<DbtDebtDto> saveDbtDebt(@RequestBody @Valid @Parameter(description = "Yeni borç nesnesi") DbtDebtSaveRequestDto dbtDebtSaveRequestDto) {
        DbtDebtDto savedDbtDebtDto = dbtDebtService.saveDbtDebt(dbtDebtSaveRequestDto);
        return ResponseEntity.ok(savedDbtDebtDto);
    }

    @PostMapping("/all/betweendates")
    @Operation(summary = "Belirtilen iki tarih arasında bulunan borçları döndürür. Filtreleme için kişi id verilebilir.")
    public ResponseEntity<List<DbtDebtDto>> getAllDbtDebtsBetweenDates(@RequestBody @Parameter(name = "Kişi Id opsiyoneldir.") DbtDebtBeetweenDatesRequestDto dbtDebtBeetweenDatesRequestDto) {
        List<DbtDebtDto> allDbtDebt = dbtDebtService.findAllDbtDebtBetweenDates(dbtDebtBeetweenDatesRequestDto);
        return ResponseEntity.ok(allDbtDebt);
    }

}
