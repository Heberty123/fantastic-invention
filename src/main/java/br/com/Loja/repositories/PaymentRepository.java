package br.com.Loja.repositories;

import br.com.Loja.dtos.CustomerPaymentDTO;
import br.com.Loja.dtos.SimpleDataDashboardDTO;
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

    @Query("SELECT new br.com.Loja.dtos.CustomerPaymentDTO(c, p) FROM payment p " +
            "LEFT JOIN FETCH p.order o LEFT JOIN FETCH o.customer c " +
            "WHERE p.paymentDate = CURRENT_DATE AND p.paid = false")
    List<CustomerPaymentDTO> findAllByCurrentPaymentDate();

    @Query("SELECT MONTH(p.paymentDate) AS name, COALESCE(SUM(p.amount), 0) AS value " +
            "FROM payment p WHERE p.paid = TRUE AND YEAR(p.paymentDate) = :year " +
            "GROUP BY MONTH(p.paymentDate) ORDER BY MONTH(p.paymentDate)")
    List<SimpleDataDashboardDTO> findPaymentDataByCurrentYear(int year);
}
