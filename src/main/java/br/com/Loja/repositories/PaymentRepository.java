package br.com.Loja.repositories;

import br.com.Loja.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM payment p " + 
           "LEFT JOIN p.order o " + 
           "LEFT JOIN o.customer c " + 
           "WHERE c.id = :id AND p.paid = :paid")
    List<Payment> findPaymentsByCustomerId(@Param("id") Long id, @Param("paid") Boolean paid);
}
