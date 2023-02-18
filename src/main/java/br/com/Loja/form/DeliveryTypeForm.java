package br.com.Loja.form;

import br.com.Loja.models.DeliveryType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeliveryTypeForm {

    private Long id;
    private String name;

    public DeliveryTypeForm(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public DeliveryType toDeliveryType(){
        return new DeliveryType(id, name);
    }
}
