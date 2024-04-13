package br.com.Loja.repositories;

import br.com.Loja.dtos.BrandFinalValueDTO;
import br.com.Loja.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query("SELECT b.id AS id, b.name AS name, COALESCE(SUM(po.netAmount), 0) AS value " +
            "FROM brand b JOIN b.products p JOIN p.productOrders po JOIN po.order o " +
            "WHERE o.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY b.id, b.name ORDER BY COALESCE(SUM(po.netAmount), 0) DESC")
    List<BrandFinalValueDTO> findAllFinalValue(LocalDateTime startDate, LocalDateTime endDate);
}
