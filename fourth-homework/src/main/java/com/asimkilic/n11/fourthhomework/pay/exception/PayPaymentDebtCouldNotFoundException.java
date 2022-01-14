package com.asimkilic.n11.fourthhomework.pay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PayPaymentDebtCouldNotFoundException extends RuntimeException {
    public PayPaymentDebtCouldNotFoundException(String message) {
        super(message);
    }
}
