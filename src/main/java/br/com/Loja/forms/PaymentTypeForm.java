package br.com.Loja.forms;

import br.com.Loja.models.PaymentType;

public record PaymentTypeForm (
        Long id,
        String name
) {
    public PaymentTypeForm(PaymentType paymentType){
        this(
                paymentType.getId(),
                paymentType.getName()
        );
    }

    public PaymentType toPaymentType() {
        return new PaymentType(id, name);
    }
}
