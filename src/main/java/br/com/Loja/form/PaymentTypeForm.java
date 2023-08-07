package br.com.Loja.form;

import br.com.Loja.models.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTypeForm {

    private Long id;

    private String name;

    public PaymentType toPaymentType(){
        return new PaymentType(id, name);
    }
}
