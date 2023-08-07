package br.com.Loja.form;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm {

    private String status;

    private Long customerId;

    private BigDecimal grossAmount;

    private BigDecimal netAmount;

    private BigDecimal discounts;

    private Set<ProductsOrdersForm> productsOrders = new HashSet<>();

    private List<PaymentForm> payments = new ArrayList<>();
}
