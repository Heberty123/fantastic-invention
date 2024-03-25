package br.com.Loja.controllers;

import java.util.List;

import br.com.Loja.dtos.CustomerPaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Loja.dtos.PaymentDTO;
import br.com.Loja.forms.PaymentForm;
import br.com.Loja.services.PaymentService;

@RestController
@RequestMapping("api/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping("/today")
    public ResponseEntity<List<CustomerPaymentDTO>> findAllPaymentsToday(){
        return new ResponseEntity<>(this.service.getPaymentsToday(), HttpStatus.OK);
    }

    @GetMapping("/{id}/{paid}")
    public ResponseEntity<List<PaymentDTO>> findAllUnpaids(@PathVariable Long id, @PathVariable boolean paid){

        List<PaymentDTO> dtos = this.service.getPaymentsByCustomerId(id, paid);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/pay")
    public ResponseEntity<PaymentDTO> payNow(@RequestBody PaymentForm paymentForm){

        PaymentDTO dto = this.service.payNow(paymentForm);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
