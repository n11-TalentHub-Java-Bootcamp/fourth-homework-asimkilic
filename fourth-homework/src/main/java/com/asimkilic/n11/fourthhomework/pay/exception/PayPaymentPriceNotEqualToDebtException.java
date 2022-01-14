package com.asimkilic.n11.fourthhomework.pay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PayPaymentPriceNotEqualToDebtException extends RuntimeException{
    public PayPaymentPriceNotEqualToDebtException(String message) {
        super(message);
    }
}
