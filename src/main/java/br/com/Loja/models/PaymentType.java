package br.com.Loja.models;


import br.com.Loja.dto.PaymentTypeDTO;
import br.com.Loja.form.PaymentTypeForm;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "paymentType")
    private Set<Payment> payments = new HashSet<Payment>();

    public PaymentType(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public PaymentType(Long id){
        this.id = id;
    }
}
