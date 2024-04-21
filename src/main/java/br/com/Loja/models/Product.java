package br.com.Loja.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String reference;

    @Column(unique = true)
    private String barcode;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private ProductType productType;

    private BigDecimal price;

    private Integer quantity;

    private Integer min_quantity;

    private Integer max_quantity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductOrders> productOrders;

    public Product(Long id, String name, String description, String reference, String barcode, Brand brand, ProductType productType, BigDecimal price,
        Integer quantity, Integer min_quantity, Integer max_quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.reference = reference;
        this.barcode = barcode;
        this.brand = brand;
        this.productType = productType;
        this.price = price;
        this.quantity = quantity;
        this.min_quantity = min_quantity;
        this.max_quantity = max_quantity;
    }

    public Product(Long id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
