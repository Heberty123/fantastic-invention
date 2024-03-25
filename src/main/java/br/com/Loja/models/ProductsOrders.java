package br.com.Loja.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products_orders")
public class ProductsOrders {

    @EmbeddedId
    private ProductsOrdersKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private BigDecimal netAmount;

    private BigDecimal grossAmount;

    private Integer quantity;

    @Column(precision = 5, scale = 4)
    private BigDecimal discounts;

    @Column(precision = 5, scale = 4)
    private BigDecimal promotion;

    private Boolean isRefund;

    public ProductsOrders(Order order, Product product, BigDecimal netAmount, BigDecimal grossAmount, Integer quantity, BigDecimal discounts, Boolean isRefund){
        this.order = order;
        this.product = product;
        this.netAmount = netAmount;
         this.grossAmount = grossAmount;
        this.quantity = quantity;
        this.discounts = discounts;
        this.isRefund = isRefund;
        this.id = new ProductsOrdersKey(order.getId(), product.getId());
    }

}