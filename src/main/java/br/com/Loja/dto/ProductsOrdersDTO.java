package br.com.Loja.dto;

import br.com.Loja.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductsOrdersDTO {

    private SimpleProductDTO product;
    private Integer quantity;

    private BigDecimal discounts;

    private Boolean isRefund;

    public ProductsOrdersDTO(Product product, Integer quantity, BigDecimal discounts, Boolean isRefund){
        this.product = new SimpleProductDTO(product);
        this.quantity = quantity;
        this.discounts = discounts;
        this.isRefund = isRefund;
    }
}
