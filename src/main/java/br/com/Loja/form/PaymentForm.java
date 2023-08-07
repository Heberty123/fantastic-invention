package br.com.Loja.form;

import lombok.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentForm {

    private Long id;

    private BigDecimal amount;

    private Calendar paymentDate;

    private PaymentTypeForm paymentType;

}
