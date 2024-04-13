package br.com.Loja.exception.handlers;

import br.com.Loja.exception.InsufficientQuantityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.Loja.exception.EntityNotFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class HandlerExceptions {
    

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.valueOf(ex.codeError))
                .body(ex.getMessage());
    }

    @ExceptionHandler({InsufficientQuantityException.class})
    public ResponseEntity<Object> handleInsufficientQuantityException(InsufficientQuantityException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", ex.getMessage());
        map.put("products", ex.getMissing());
        return ResponseEntity
                .status(HttpStatus.valueOf(ex.getCode()))
                .body(map);
    }
}
