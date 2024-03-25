package br.com.Loja.dtos;

import java.math.BigDecimal;

public record BrandFinalValueDTO(
        Long id,
        String name,
        BigDecimal value
) { }
