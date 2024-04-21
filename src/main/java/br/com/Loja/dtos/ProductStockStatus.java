package br.com.Loja.dtos;

public interface ProductStockStatus {

    Long getId();
    String getName();
    String getStatus();
    Integer getQuantity();
    Integer getMin_quantity();
    Integer getMax_quantity();
}
