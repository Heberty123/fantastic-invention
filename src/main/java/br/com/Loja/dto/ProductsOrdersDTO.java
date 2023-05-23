package br.com.Loja.dto;

import br.com.Loja.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductsOrdersDTO {

    private ProductDTO product;
    private Integer quantity;

    public ProductsOrdersDTO(Product product, Integer quantity){
        this.product = new ProductDTO(product);
        this.quantity = quantity;
    }
}
