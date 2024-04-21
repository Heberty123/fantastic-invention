package br.com.Loja.forms;

import br.com.Loja.models.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record ProductTypeForm (
        Long id,
        String name
) {
    public ProductType toProductType(){
        return new ProductType(
                this.id,
                this.name
        );
    }
}
