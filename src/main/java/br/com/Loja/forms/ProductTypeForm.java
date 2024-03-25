package br.com.Loja.forms;

import br.com.Loja.models.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeForm {

    private Long id;

    private String name;

    public ProductType toProductType(){
        return new ProductType(
                this.id,
                this.name
        );
    }
}
