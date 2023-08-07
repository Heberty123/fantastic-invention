package br.com.Loja.controllers;

import br.com.Loja.dto.PaymentTypeDTO;
import br.com.Loja.form.PaymentTypeForm;
import br.com.Loja.models.PaymentType;
import br.com.Loja.repositories.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/paymentType")
public class PaymentTypeController {

    @Autowired
    private PaymentTypeRepository repository;

    @GetMapping
    public ResponseEntity<List<PaymentTypeDTO>> findAll(){

        List<PaymentType> paymentTypes =
                this.repository.findAll();

        if(paymentTypes.isEmpty())
            return ResponseEntity.notFound().build();

        List<PaymentTypeDTO> dtos =
                paymentTypes.stream()
                        .map(PaymentTypeDTO::new)
                        .toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<PaymentTypeDTO> save(@RequestBody
                                                   PaymentTypeForm paymentTypeForm){

        PaymentType paymentType = paymentTypeForm.toPaymentType();
        PaymentTypeDTO dto = new PaymentTypeDTO(this.repository.save(paymentType));

        return ResponseEntity.ok(dto);
    }
}
