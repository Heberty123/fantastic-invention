package br.com.Loja.form;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm {

    private String status;

    private Long customerId;

    private Set<ProductsOrdersForm> productsOrders = new HashSet<>();
}
