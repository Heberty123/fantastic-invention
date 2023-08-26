package br.com.Loja.controllers;

import br.com.Loja.dto.PaymentDTO;
import br.com.Loja.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping("/{id}/{paid}")
    public ResponseEntity<List<PaymentDTO>> findAllUnpaids(@PathVariable Long id, @PathVariable boolean paid){

        List<PaymentDTO> dtos = this.service.getPaymentsByCustomerId(id, paid);

        if(dtos.isEmpty())
            return ResponseEntity.notFound().build();

        return new ResponseEntity<List<PaymentDTO>>(dtos, HttpStatus.OK);
    }
}
