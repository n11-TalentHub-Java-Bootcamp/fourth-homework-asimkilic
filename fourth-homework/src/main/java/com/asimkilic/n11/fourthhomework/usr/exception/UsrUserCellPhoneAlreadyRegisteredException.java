package com.asimkilic.n11.fourthhomework.usr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsrUserCellPhoneAlreadyRegisteredException extends RuntimeException {
    public UsrUserCellPhoneAlreadyRegisteredException(String message) {
        super(message);
    }
}

