package br.com.Loja.repositories;

import br.com.Loja.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Set<Order> findAllByCustomerId(Long customerId);
}
