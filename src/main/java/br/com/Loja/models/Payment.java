package br.com.Loja.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;

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

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime payedAt;

    private BigDecimal amountPayed;

    private Boolean paid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

}
