package br.com.Loja.dtos;

import br.com.Loja.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SimpleProductDTO {

    private Long id;

    private String name;

    private BigDecimal price;

    public SimpleProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
    }
}
