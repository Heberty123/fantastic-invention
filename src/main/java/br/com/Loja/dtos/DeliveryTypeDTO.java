package br.com.Loja.dtos;

import br.com.Loja.models.DeliveryType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeliveryTypeDTO {

    private Long id;

    private String name;

    public DeliveryTypeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public DeliveryTypeDTO(DeliveryType deliveryType) {
        id = deliveryType.getId();
        name = deliveryType.getName();
    }
}
