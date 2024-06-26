package br.com.Loja.dtos;

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

    private boolean paid;

    private BigDecimal netAmount;

    private BigDecimal grossAmount;

    private BigDecimal discounts;

    private Set<ProductOrdersDTO> productOrders;

    private List<PaymentDTO> payments;

    private String createdAt;

    public OrderDTO(Order order){
        this.id = order.getId();
        this.paid = order.isPaid();
        this.netAmount = order.getNetAmount();
        this.grossAmount = order.getGrossAmount();
        this.discounts = order.getDiscounts();
        this.productOrders = order.getProductOrders()
                .stream()
                .map(v -> {
                    return new ProductOrdersDTO(
                            v.getProduct(),
                            v.getNetAmount(),
                            v.getGrossAmount(),
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
