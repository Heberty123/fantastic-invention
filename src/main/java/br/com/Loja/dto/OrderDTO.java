package br.com.Loja.dto;

import br.com.Loja.models.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Long id;

    private String status;

    private BigDecimal netAmount;

    private BigDecimal grossAmount;

    private BigDecimal discounts;

    private Set<ProductsOrdersDTO> productsOrders;

    private List<PaymentDTO> payments;

    private String createdAt;

    public OrderDTO(Order order){
        this.id = order.getId();
        this.status = order.getStatus();
        this.netAmount = order.getNetAmount();
        this.grossAmount = order.getGrossAmount();
        this.discounts = order.getDiscounts();
        this.productsOrders = order.getProductsOrders()
                .stream()
                .map(v -> {
                    return new ProductsOrdersDTO(
                            v.getProduct(),
                            v.getQuantity(),
                            v.getDiscounts(),
                            v.getIsRefund()
                    );
                })
                .collect(Collectors.toSet());
        this.payments = order.getPayments()
                .stream()
                .map(PaymentDTO::new)
                .collect(Collectors.toList());
        this.createdAt = order.getCreatedAt().toString();
    }
}
