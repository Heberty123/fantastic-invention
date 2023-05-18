package br.com.Loja.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Set;

@Data
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductType productType;

    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    private Set<ProductsOrders> productsOrders;

    public Product(Long id, String name, String description, String reference, String barcode, Brand brand, ProductType productType, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.reference = reference;
        this.barcode = barcode;
        this.brand = brand;
        this.productType = productType;
        this.price = price;
    }
}
