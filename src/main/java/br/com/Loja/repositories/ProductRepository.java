package br.com.Loja.repositories;

import br.com.Loja.dtos.ProductFinalValueDTO;
import br.com.Loja.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByBarcode(String barcode);

    @Query("SELECT new br.com.Loja.dtos.ProductFinalValueDTO(p.id, p.name, b.name, COALESCE(SUM(po.netAmount), 0)) " +
            " FROM product p LEFT JOIN p.brand b LEFT JOIN p.productsOrders po GROUP BY p.id," +
            " p.name, b.name ORDER BY COALESCE(SUM(po.netAmount), 0) DESC")
    List<ProductFinalValueDTO> findAllFinalValue();
}
