package br.com.Loja.forms;

import br.com.Loja.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForm {

    private Long id;

    private String name;

    private String description;

    private String reference;

    private String barcode;

    private BrandForm brand;

    private ProductTypeForm productType;

    private BigDecimal price;

    private Integer quantity;

    private Integer min_quantity;

    private Integer max_quantity;

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
