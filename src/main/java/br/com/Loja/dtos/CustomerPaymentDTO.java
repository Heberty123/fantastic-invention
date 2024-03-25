package br.com.Loja.dtos;

import br.com.Loja.forms.CustomerForm;
import br.com.Loja.forms.PaymentTypeForm;
import br.com.Loja.models.Customer;
import br.com.Loja.models.Payment;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;

public record CustomerPaymentDTO(
        CustomerForm customer,
        Long id,
        BigDecimal amount,
        Calendar paymentDate,
        PaymentTypeForm paymentType,
        LocalDateTime payedAt,
        BigDecimal amountPayed,
        Boolean paid
) {
    public CustomerPaymentDTO(Customer customer, Payment payment) {
        this(
                new CustomerForm(customer),
                payment.getId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getPaymentType() == null ?
                    null : new PaymentTypeForm(payment.getPaymentType()),
                payment.getPayedAt(),
                payment.getAmountPayed(),
                payment.getPaid()
        );
    }
}
