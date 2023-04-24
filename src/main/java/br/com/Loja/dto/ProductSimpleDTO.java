package br.com.Loja.dto;

import br.com.Loja.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductSimpleDTO {

    private Long id;

    private String name;

    private BigDecimal price;

    public ProductSimpleDTO(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
    }
}
