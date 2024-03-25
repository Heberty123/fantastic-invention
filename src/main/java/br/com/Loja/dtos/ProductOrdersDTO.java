package br.com.Loja.dtos;

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
public class ProductOrdersDTO {

    private SimpleProductDTO product;

    private BigDecimal netAmount;

    private BigDecimal grossAmount;

    private Integer quantity;

    private BigDecimal discounts;

    private Boolean isRefund;

    public ProductOrdersDTO(Product product, BigDecimal netAmount, BigDecimal grossAmount, Integer quantity, BigDecimal discounts, Boolean isRefund){
        this.product = new SimpleProductDTO(product);
        this.netAmount = netAmount;
        this.grossAmount = grossAmount;
        this.quantity = quantity;
        this.discounts = discounts;
        this.isRefund = isRefund;
    }
}
