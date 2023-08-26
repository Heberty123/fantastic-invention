package br.com.Loja.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.Loja.dto.PaymentDTO;
import br.com.Loja.models.Payment;
import br.com.Loja.repositories.PaymentRepository;

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository repository;

    public List<PaymentDTO> getPaymentsByCustomerId(Long id, boolean paid){

        List<Payment> payments =
                this.repository.findPaymentsByCustomerId(id, paid);         

        return payments.stream()
                        .map(PaymentDTO::new)
                        .toList();
    }
}
