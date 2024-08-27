package br.com.Loja.services;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import br.com.Loja.exception.EntityNotFoundException;
import br.com.Loja.dtos.CustomerPaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Loja.dtos.PaymentDTO;
import br.com.Loja.forms.PaymentForm;
import br.com.Loja.models.Payment;
import br.com.Loja.repositories.PaymentRepository;

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository repository;

    @Autowired
    private OrderService orderService;

    public Payment getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment", id));
    }

    public List<CustomerPaymentDTO> getPaymentsToday() {
        return repository.findAllByCurrentPaymentDate();
    }

    public List<PaymentDTO> getPaymentsByCustomerId(Long id, boolean paid){

        List<Payment> payments =
                this.repository.findPaymentsByCustomerId(id, paid);         

        return payments.stream()
                        .map(PaymentDTO::new)
                        .toList();
    }

    public PaymentDTO payNow(PaymentForm paymentForm){

        Payment payment = getById(paymentForm.getId());
        payment.setPaymentType(paymentForm.getPaymentType().toPaymentType());
        payment.setAmountPayed(paymentForm.getAmountPayed());
        payment.setPayedAt(Calendar.getInstance());
        payment.setPaid(true);
        payment = repository.save(payment);
        orderService.validOrderPaymentById(payment.getOrder().getId());
        return new PaymentDTO(payment);
    }


}
