package br.com.Loja.repositories;

import br.com.Loja.dtos.CustomerPurchaseDTO;
import br.com.Loja.dtos.DefaultingCustomerDTO;
import br.com.Loja.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByCpf(String cpf);
    List<Customer> findAllByParentCustomerId(Long id);

    @Query("SELECT c.id AS id, c.name AS name, c.cpf AS cpf, " +
            "COALESCE(SUM(o.netAmount), 0) AS value FROM Customer c " +
            "JOIN c.orders o WHERE o.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY c.id, c.name, c.cpf ORDER BY COALESCE(SUM(o.netAmount), 0) DESC")
    List<CustomerPurchaseDTO> getCustomerPurchase(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT c.id AS id, c.name AS name, c.cpf AS cpf, " +
            "SUM(p.amount) AS pendingAmount, " +
            "COUNT(p.paid) AS pendingPaymentTotal, " +
            "MIN(p.paymentDate) AS debtSince " +
            "FROM Customer c " +
            "JOIN c.orders o JOIN o.payments p " +
            "WHERE p.paid = FALSE AND p.paymentDate < CURRENT_DATE " +
            "GROUP BY c.id, c.name, c.cpf")
    List<DefaultingCustomerDTO> getDefaultingCustomer();
}
