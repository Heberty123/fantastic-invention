package br.com.Loja.dto;

import br.com.Loja.models.ProductType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductTypeDTO {

    private Long id;

    private String name;

    public ProductTypeDTO(ProductType chip){
        id = chip.getId();
        name = chip.getName();
    }
}
