package br.com.Loja.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class DeliveryType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "deliveryType", fetch = FetchType.LAZY)
    List<Address> addresses = new ArrayList<>();

    public DeliveryType(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
