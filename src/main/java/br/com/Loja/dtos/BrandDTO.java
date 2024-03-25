package br.com.Loja.dtos;

import br.com.Loja.models.Brand;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrandDTO {

    private Long id;

    private String name;

    public BrandDTO(Brand brand){
        this.id = brand.getId();
        this.name = brand.getName();
    }
}
