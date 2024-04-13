package br.com.Loja.dtos;

import java.time.LocalDateTime;
import java.util.Calendar;

public interface DefaultingCustomerDTO {
        Long getId();
        String getName();
        String getCpf();
        Double getPendingAmount();
        Integer getPendingPaymentTotal();
        Calendar getDebtSince();
}
