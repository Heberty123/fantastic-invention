package br.com.Loja.repositories;

import br.com.Loja.dtos.ProductDashboard;
import br.com.Loja.dtos.ProductStockStatus;
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
    void decrementQuantityById(Integer qty, Long id);

    @Modifying
    @Transactional
    @Query("UPDATE product p set p.quantity = :qty WHERE p.id = :id")
    void setStockById(Integer qty, Long id);

    @Query("SELECT p.id AS id, p.name AS name, CASE WHEN(p.quantity < p.min_quantity) THEN 'MENOR' ELSE 'MAIOR' END AS status, " +
            "p.quantity AS quantity, p.min_quantity AS min_quantity, p.max_quantity AS max_quantity FROM product p WHERE " +
            "p.quantity < p.min_quantity OR p.quantity > p.max_quantity")
    List<ProductStockStatus> findAllStockStatus();
}
