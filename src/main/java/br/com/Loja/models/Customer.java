package br.com.Loja.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.Loja.forms.CustomerForm;

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

    @ManyToOne
    @JoinColumn(updatable = false)
    private Customer parentCustomer;

    @OneToMany(mappedBy = "parentCustomer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Customer> dependents = new HashSet<Customer>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<Order>();


    public Customer(CustomerForm form) {
        this.id = form.id();
        this.name = form.name();
        this.cpf = form.cpf();
    }

    public Customer(Long id){
        this.id = id;
    }

}
