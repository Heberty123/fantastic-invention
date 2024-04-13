package br.com.Loja.controllers;

import br.com.Loja.dtos.PaymentTypeDTO;
import br.com.Loja.dtos.PaymentTypeDashboardDTO;
import br.com.Loja.dtos.ProductDashboard;
import br.com.Loja.forms.PaymentTypeForm;
import br.com.Loja.models.PaymentType;
import br.com.Loja.repositories.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("api/paymentTypes")
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
    public ResponseEntity<PaymentTypeDTO> save(@RequestBody PaymentTypeForm paymentTypeForm){

        PaymentType paymentType = paymentTypeForm.toPaymentType();
        PaymentTypeDTO dto = new PaymentTypeDTO(this.repository.save(paymentType));
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<List<PaymentTypeDashboardDTO>> getDashboard(
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Calendar startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Calendar endDate
    ) {
        System.out.println(startDate);
        System.out.println(endDate);
        return new ResponseEntity<>(repository.getDashboard(startDate, endDate), HttpStatus.OK);
    }
}
