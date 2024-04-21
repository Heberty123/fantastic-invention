package br.com.Loja.forms;

import br.com.Loja.models.Brand;
import lombok.*;

public record BrandForm (
        Long id,
        String name
) {

    public Brand toBrand(){
        return new Brand(this.id, this.name);
    }
}
