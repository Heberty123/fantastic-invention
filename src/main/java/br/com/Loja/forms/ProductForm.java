package br.com.Loja.forms;

import br.com.Loja.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public record ProductForm (
        Long id,
        String name,
        String description,
        String reference,
        String barcode,
        BrandForm brand,
        ProductTypeForm productType,
        BigDecimal price,
        Integer quantity,
        Integer min_quantity,
        Integer max_quantity
) {

    public Product toProduct(){
        return new Product(
                this.id,
                this.name,
                this.description,
                this.reference,
                this.barcode,
                this.brand.toBrand(),
                this.productType.toProductType(),
                this.price,
                this.quantity,
                this.min_quantity,
                this.max_quantity
        );
    }
}
