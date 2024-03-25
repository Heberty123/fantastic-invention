package br.com.Loja.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("_paid")
    private boolean paid;

    @JsonProperty("_customerId")
    private Long customerId;

    @JsonProperty("_grossAmount")
    private BigDecimal grossAmount;

    @JsonProperty("_netAmount")
    private BigDecimal netAmount;

    @JsonProperty("_discounts")
    private BigDecimal discounts;

    @JsonProperty("_productOrders")
    private Set<ProductOrdersForm> productOrders = new HashSet<>();

    @JsonProperty("_payments")
    private List<PaymentForm> payments = new ArrayList<>();
}
