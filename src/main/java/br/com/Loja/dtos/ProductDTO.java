package br.com.Loja.dtos;


import java.math.BigDecimal;
import br.com.Loja.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private String reference;

    private String barcode;

    private String brand;

    private String productType;

    private BigDecimal price;

    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        reference = product.getReference();
        barcode = product.getBarcode();
        brand = product.getBrand().getName();
        productType = product.getProductType().getName();
        price = product.getPrice();
    }
}
