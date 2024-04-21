package br.com.Loja.dtos;


import java.math.BigDecimal;
import br.com.Loja.models.Product;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record ProductDTO (
        Long id,
        String name,
        String description,
        String reference,
        String barcode,
        BrandDTO brand,
        ProductTypeDTO productType,
        BigDecimal price,
        Integer quantity,
        Integer min_quantity,
        Integer max_quantity
) {

    public ProductDTO(Product product) {
        this(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getReference(),
            product.getBarcode(),
            new BrandDTO(product.getBrand()),
            new ProductTypeDTO(product.getProductType()),
            product.getPrice(),
            product.getQuantity(),
            product.getMin_quantity(),
            product.getMax_quantity()
        );
    }
}
