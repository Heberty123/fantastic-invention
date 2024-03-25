package br.com.Loja.dtos;

import java.math.BigDecimal;

public record ProductFinalValueDTO(
        Long id,
        String name,
        String brand,
        BigDecimal value
) {
}
