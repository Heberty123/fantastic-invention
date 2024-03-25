package br.com.Loja.repositories;

import br.com.Loja.dtos.BrandFinalValueDTO;
import br.com.Loja.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

}
