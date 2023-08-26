package br.com.Loja.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String cpf;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<Address>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false)
    private Customer parentCustomer;

    @OneToMany(mappedBy = "parentCustomer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Customer> dependents = new HashSet<Customer>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<Order>();


    public Customer(Long id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }

    public Customer(Long id){
        this.id = id;
    }

}
