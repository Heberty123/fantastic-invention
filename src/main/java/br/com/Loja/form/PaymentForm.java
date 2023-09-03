package br.com.Loja.form;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;

import br.com.Loja.models.Payment;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentForm {

    private Long id;

    private BigDecimal amount;

    private Calendar paymentDate;

    private PaymentTypeForm paymentType;

    private LocalDateTime payedAt;

    private BigDecimal amountPayed;

    private Boolean paid;

    public Payment toPayment(){
        return new Payment(
            this.id,
            this.paymentDate,
            this.amount,
            this.paymentType.toPaymentType(),
            this.payedAt,
            this.amountPayed,
            this.paid,
            null
            );
    }

}
