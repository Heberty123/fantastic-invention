package br.com.Loja.form;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsOrdersForm {

    private Long productId;

    private Integer quantity;
}
