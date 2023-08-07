package br.com.Loja.repositories;

import br.com.Loja.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByOrderCustomerId(Long id);
}
