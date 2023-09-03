package br.com.Loja.repositories;

import br.com.Loja.models.Payment;
import br.com.Loja.models.PaymentType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM payment p " + 
           "LEFT JOIN p.order o " + 
           "LEFT JOIN o.customer c " + 
           "WHERE c.id = :id AND p.paid = :paid")
    List<Payment> findPaymentsByCustomerId(@Param("id") Long id, @Param("paid") Boolean paid);

    @Modifying
    @Query("UPDATE payment p SET p.paymentType = :paymentType, " +
           "p.amountPayed = :amountPayed, p.paid = true WHERE p.id = :id")
    void payNow(@Param(value = "paymentType") PaymentType paymentType,
                @Param(value = "amountPayed") BigDecimal amountPayed,
                @Param(value = "id") Long id);
}
