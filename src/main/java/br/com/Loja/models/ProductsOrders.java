package br.com.Loja.models;

import jakarta.persistence.*;

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
}
