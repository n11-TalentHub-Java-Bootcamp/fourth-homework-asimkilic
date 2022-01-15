package com.asimkilic.n11.fourthhomework.exception;

import com.asimkilic.n11.fourthhomework.dbt.exception.DbtDebtFallDueOnCantBeforeNowException;
import com.asimkilic.n11.fourthhomework.dbt.exception.DbtDebtNotFoundException;
import com.asimkilic.n11.fourthhomework.pay.exception.PayPaymentDebtCouldNotFoundException;
import com.asimkilic.n11.fourthhomework.pay.exception.PayPaymentPriceNotEqualToDebtException;
import com.asimkilic.n11.fourthhomework.usr.exception.UsrUserCellPhoneAlreadyRegisteredException;
import com.asimkilic.n11.fourthhomework.usr.exception.UsrUserNotFoundException;
import com.asimkilic.n11.fourthhomework.usr.exception.UsrUserTcknAlreadyRegisteredException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NotNull MethodArgumentNotValidException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                }
        );
        return new ResponseEntity<>(errors, BAD_REQUEST);
    }

    @ExceptionHandler(UsrUserNotFoundException.class)
    public ResponseEntity<?> usrUserNotFoundExceptionHandler(UsrUserNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(UsrUserCellPhoneAlreadyRegisteredException.class)
    public ResponseEntity<?> usrUserCellPhoneAlreadyRegisteredException(UsrUserCellPhoneAlreadyRegisteredException exception) {
        return new ResponseEntity<>(exception.getMessage(), CONFLICT);
    }

    @ExceptionHandler(UsrUserTcknAlreadyRegisteredException.class)
    public ResponseEntity<?> usrUserTcknAlreadyRegisteredException(UsrUserTcknAlreadyRegisteredException exception) {
        return new ResponseEntity<>(exception.getMessage(), CONFLICT);
    }


    @ExceptionHandler(DbtDebtFallDueOnCantBeforeNowException.class)
    public ResponseEntity<?> dbtDebtFallDueOnCantBeforeNowException(DbtDebtFallDueOnCantBeforeNowException exception) {
        return new ResponseEntity<>(exception.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(DbtDebtNotFoundException.class)
    public ResponseEntity<?> DbtDebtNotFoundException(DbtDebtNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }
    @ExceptionHandler(PayPaymentDebtCouldNotFoundException.class)
    public ResponseEntity<?> payPaymentDebtCouldNotFoundException(PayPaymentDebtCouldNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(PayPaymentPriceNotEqualToDebtException.class)
    public ResponseEntity<?> payPaymentPriceNotEqualToDebtException(PayPaymentPriceNotEqualToDebtException exception) {
        return new ResponseEntity<>(exception.getMessage(), BAD_REQUEST);
    }

}

