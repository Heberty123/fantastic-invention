package br.com.Loja.form;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsOrdersForm {

    private Long productId;

    private Integer quantity;

    private BigDecimal discounts;

    private Boolean isRefund;
}
