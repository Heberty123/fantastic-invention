package br.com.Loja.dtos;

import br.com.Loja.models.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long id;
    private BigDecimal amount;
    private Calendar paymentDate;
    private PaymentTypeDTO paymentType;
    private LocalDateTime payedAt;
    private BigDecimal amountPayed;
    private Boolean paid;

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.amount = payment.getAmount();
        this.paymentDate = payment.getPaymentDate();
        this.paymentType = payment.getPaymentType() != null ?
                new PaymentTypeDTO(payment.getPaymentType())
                :
                null;
        this.payedAt = payment.getPayedAt();
        this.amountPayed = payment.getAmountPayed();
        this.paid = payment.getPaid();
    }
}
