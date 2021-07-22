package com.app.neueda.atm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author vipin kumar
 * 
 * Exception handler responsible for handling exceptions
 * and returning meaningful messages to users
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
     
    @ExceptionHandler(value = AccountDetailsException.class)
    public ResponseEntity wrongAccountDetails(AccountDetailsException accountDetailsException) {
        return new ResponseEntity(accountDetailsException.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = TransactionException.class)
    public ResponseEntity transactionException(TransactionException transactionException) {
        return new ResponseEntity(transactionException.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity generalException(Exception exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
  
}