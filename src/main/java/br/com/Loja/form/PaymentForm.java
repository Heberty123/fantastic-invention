package br.com.Loja.form;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;

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

}
