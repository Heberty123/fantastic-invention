package br.com.Loja.repositories;

import br.com.Loja.dtos.BrandFinalValueDTO;
import br.com.Loja.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query("SELECT new br.com.Loja.dtos.BrandFinalValueDTO(b.id, b.name, COALESCE(SUM(po.netAmount), 0)) " +
            "FROM brand b LEFT JOIN b.products p LEFT JOIN p.productsOrders po GROUP BY b.id, " +
            "b.name ORDER BY COALESCE(SUM(po.netAmount), 0) DESC")
    List<BrandFinalValueDTO> findAllFinalValue();
}
