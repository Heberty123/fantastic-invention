package br.com.Loja.models;

import br.com.Loja.form.OrderForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    private BigDecimal netAmount;

    private BigDecimal grossAmount;

    @Column(precision = 5, scale = 4)
    private BigDecimal discounts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<ProductsOrders> productsOrders = new HashSet<ProductsOrders>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<Payment>();

    private LocalDateTime createdAt;

}
