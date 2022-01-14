package com.asimkilic.n11.fourthhomework.pay.controller;

import com.asimkilic.n11.fourthhomework.pay.dto.PayPaymentDto;
import com.asimkilic.n11.fourthhomework.pay.dto.PayPaymentSaveRequestDto;
import com.asimkilic.n11.fourthhomework.pay.service.PayPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name="Ödeme İşlemleri",description = "PayPayment API")
public class PayPaymentController {
    private final PayPaymentService payPaymentService;

    @GetMapping
    @Operation(summary = "Bütün ödemeleri listeler")
    public ResponseEntity<List<PayPaymentDto>> getAllPayPayments(){
        List<PayPaymentDto> allPayPayments=payPaymentService.findAllPayPayment();
        return ResponseEntity.ok(allPayPayments);
    }
    @PostMapping
    @Operation(summary = "Borç ödemesi yapar",description = "ödeme işlemleri")
    public ResponseEntity<PayPaymentDto> savePayPayment(@RequestBody @Valid @Parameter(description = "Yeni ödeme nesnesi")PayPaymentSaveRequestDto payPaymentSaveRequestDto){
        PayPaymentDto savedPayPayment= payPaymentService.savePayPayment(payPaymentSaveRequestDto);
    return ResponseEntity.ok(savedPayPayment);
    }
}
