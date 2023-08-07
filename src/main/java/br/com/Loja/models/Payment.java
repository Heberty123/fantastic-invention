package br.com.Loja.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Calendar paymentDate;

    private BigDecimal amount;

    @ManyToOne(cascade = CascadeType.ALL)
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

}
