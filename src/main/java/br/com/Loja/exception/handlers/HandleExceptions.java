package br.com.Loja.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.Loja.exception.EntityNotFoundException;

@ControllerAdvice
public class HandleExceptions {
    

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.valueOf(ex.codeError))
                .body(ex.getMessage());
    }
}
