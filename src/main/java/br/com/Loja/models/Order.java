package br.com.Loja.models;

import br.com.Loja.form.OrderForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<ProductsOrders> productsOrders = new HashSet<ProductsOrders>();

    @ManyToOne()
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<Payment> payments = new ArrayList<Payment>();

}
