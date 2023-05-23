package br.com.Loja.dto;

import br.com.Loja.models.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Long id;

    private String status;

    private Set<ProductsOrdersDTO> productsOrders;

    public OrderDTO(Order order){
        this.id = order.getId();
        this.status = order.getStatus();
        this.productsOrders = order.getProductsOrders()
                .stream()
                .map(v -> {
                    return new ProductsOrdersDTO(v.getProduct(), v.getQuantity());
                })
                .collect(Collectors.toSet());
    }
}
