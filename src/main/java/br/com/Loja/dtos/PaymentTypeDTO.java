package br.com.Loja.dtos;

import br.com.Loja.models.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTypeDTO {

    private Long id;

    private String name;

    public PaymentTypeDTO(PaymentType paymentType){
        this.id = paymentType.getId();
        this.name = paymentType.getName();
    }
}
