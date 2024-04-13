package br.com.Loja.dtos;

import java.util.List;

public record SeriesDashboardDTO(
    String name,
    List<SimpleDataDashboardDTO> series
) {}
