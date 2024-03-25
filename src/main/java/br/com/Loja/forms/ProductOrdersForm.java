package br.com.Loja.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrdersForm {

    @JsonProperty("_product")
    private SimpleProductForm product;

    @JsonProperty("_netAmount")
    private BigDecimal netAmount;

    @JsonProperty("_grossAmount")
    private BigDecimal grossAmount;

    @JsonProperty("_quantity")
    private Integer quantity;

    @JsonProperty("_discounts")
    private BigDecimal discounts;

    @JsonProperty("_isRefund")
    private Boolean isRefund;
}
