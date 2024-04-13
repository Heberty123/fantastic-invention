package br.com.Loja.repositories;

import br.com.Loja.dtos.PaymentTypeDashboardDTO;
import br.com.Loja.models.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {

    @Query("SELECT pt.id AS id, pt.name AS name, COALESCE(SUM(p.amount), 0) AS value FROM PaymentType pt " +
            "JOIN pt.payments p WHERE p.paid = TRUE AND p.paymentDate BETWEEN :start AND :end " +
            "GROUP BY pt.id, pt.name ORDER BY COALESCE(SUM(p.amount), 0) DESC")
    List<PaymentTypeDashboardDTO> getDashboard(Calendar start, Calendar end);
}
