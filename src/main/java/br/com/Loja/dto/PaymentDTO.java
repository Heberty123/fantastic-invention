package br.com.Loja.dto;

import br.com.Loja.models.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long id;
    private BigDecimal amount;
    private Calendar paymentDate;

    private PaymentTypeDTO paymentType;

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.amount = payment.getAmount();
        this.paymentDate = payment.getPaymentDate();
        this.paymentType = payment.getPaymentType() != null ?
                new PaymentTypeDTO(payment.getPaymentType())
                :
                null;
    }
}
