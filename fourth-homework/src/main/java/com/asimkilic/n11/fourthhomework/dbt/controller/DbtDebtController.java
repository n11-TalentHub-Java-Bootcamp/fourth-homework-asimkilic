package com.asimkilic.n11.fourthhomework.dbt.controller;


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
import java.util.List;

@RestController
@RequestMapping("/api/v1/debts")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Borç İşlemleri", description = "DbtDebt API")

public class DbtDebtController {

    private final DbtDebtService dbtDebtService;
    @GetMapping
    @Operation(summary="Bütün kişilerin borçlarını döndürür.")
    public ResponseEntity<List<DbtDebtDto>> getAllDbtDebs(){
        List<DbtDebtDto> allDbtDebt = dbtDebtService.findAllDbtDebt();
        return ResponseEntity.ok(allDbtDebt);
    }

    @PostMapping
    @Operation(summary = "Yeni borç kaydeder, gecikme borcu kaydetmek için kullanılmaz")
    public ResponseEntity<DbtDebtDto> saveDbtDebt(@RequestBody @Valid @Parameter(name = "Yeni borç nesnesi")DbtDebtSaveRequestDto dbtDebtSaveRequestDto){
        DbtDebtDto savedDbtDebtDto = dbtDebtService.saveDbtDebt(dbtDebtSaveRequestDto);
        return ResponseEntity.ok(savedDbtDebtDto);
    }
}
