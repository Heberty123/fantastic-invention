package br.com.Loja.repositories;

import br.com.Loja.dtos.ProductTypeDashboard;
import br.com.Loja.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    @Query("SELECT pt.id AS id, pt.name AS name, COALESCE(SUM(po.netAmount), 0) as value " +
            "FROM productType pt JOIN pt.products p JOIN p.productOrders po JOIN po.order o " +
            "WHERE o.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY pt.id, pt.name ORDER BY COALESCE(SUM(po.netAmount), 0) DESC")
    List<ProductTypeDashboard> getDashboard(LocalDateTime startDate, LocalDateTime endDate);

}
