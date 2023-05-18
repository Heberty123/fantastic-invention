package br.com.Loja.form;

import br.com.Loja.models.Brand;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrandForm {

    private Long id;
    private String name;

    public Brand toBrand(){
        return new Brand(id, name);
    }
}
