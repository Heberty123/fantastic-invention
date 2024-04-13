package br.com.Loja.repositories;

import br.com.Loja.dtos.ProductDashboard;
import br.com.Loja.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByBarcode(String barcode);

    @Query("SELECT p.id AS id, p.name AS name, b.name AS brand, COALESCE(SUM(po.netAmount), 0) AS value " +
            "FROM product p JOIN p.brand b JOIN p.productOrders po JOIN po.order o " +
            "WHERE o.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY p.id, p.name, b.name ORDER BY COALESCE(SUM(po.netAmount), 0) DESC")
    List<ProductDashboard> findAllFinalValue(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT p.quantity FROM product p WHERE p.id = :id")
    Integer getQuantityById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE product p set p.quantity = (p.quantity - :qty) WHERE p.id = :id")
    void setQuantityById(Integer qty, Long id);
}
