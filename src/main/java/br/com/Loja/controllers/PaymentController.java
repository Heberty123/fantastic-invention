package br.com.Loja.controllers;

import br.com.Loja.dto.PaymentDTO;
import br.com.Loja.dto.PaymentTypeDTO;
import br.com.Loja.models.Payment;
import br.com.Loja.models.PaymentType;
import br.com.Loja.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PaymentRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<List<PaymentDTO>> findAll(@PathVariable Long id){

        List<Payment> payments =
                this.repository.findAllByOrderCustomerId(id);

        if(payments.isEmpty())
            return ResponseEntity.notFound().build();


        List<PaymentDTO> dtos =
                payments.stream()
                        .map(PaymentDTO::new)
                        .toList();

        return ResponseEntity.ok(dtos);
    }
}
