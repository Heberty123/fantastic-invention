package br.com.Loja.controllers;

import java.util.List;

import br.com.Loja.dtos.CustomerPaymentDTO;
import br.com.Loja.dtos.SeriesDashboardDTO;
import br.com.Loja.repositories.OrderRepository;
import br.com.Loja.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.Loja.dtos.PaymentDTO;
import br.com.Loja.forms.PaymentForm;
import br.com.Loja.services.PaymentService;

@RestController
@RequestMapping("api/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private OrderRepository orderRepository;

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

    @GetMapping("/dashboard")
    public ResponseEntity<List<SeriesDashboardDTO>> getDashboard(@RequestParam(name = "year") Integer year){
        SeriesDashboardDTO dto1 =
                new SeriesDashboardDTO("pedidos", this.orderRepository.findSalesDataByCurrentYear(year));
        SeriesDashboardDTO dto2 =
                new SeriesDashboardDTO("pagamentos", this.repository.findPaymentDataByCurrentYear(year));
        List<SeriesDashboardDTO> resulted = List.of(dto1, dto2);
        return new ResponseEntity<>(resulted, HttpStatus.OK);
    }
}
