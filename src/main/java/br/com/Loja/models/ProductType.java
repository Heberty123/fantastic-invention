package br.com.Loja.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "productType", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<Product>();


    public ProductType(Long id, String name){
        this.id = id;
        this.name = name;
    }

}
