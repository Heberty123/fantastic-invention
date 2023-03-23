package br.com.Loja.dto;


import br.com.Loja.models.Address;
import br.com.Loja.models.Brand;
import br.com.Loja.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private String reference;

    private Long barcode;

    private BrandDTO brand;

    private BigDecimal price;

    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        reference = product.getReference();
        barcode = product.getBarcode();
        brand = new BrandDTO(product.getBrand());
        price = product.getPrice();
    }
}
