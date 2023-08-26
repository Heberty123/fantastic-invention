package br.com.Loja.repositories;

import br.com.Loja.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM orders o " + 
           "INNER JOIN o.customer c " + 
           "WHERE c.id = :id AND o.paid = :paid")
    Set<Order> findAllByCustomerId(@Param("id") Long id, @Param("paid") boolean paid);
}
