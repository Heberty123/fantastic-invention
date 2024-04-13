package br.com.Loja.exception;

import br.com.Loja.forms.SimpleProductForm;
import lombok.Getter;
import java.util.List;

@Getter
public class InsufficientQuantityException extends RuntimeException {

    private final Integer code = 409;
    private List<SimpleProductForm> missing;

    public InsufficientQuantityException(List<SimpleProductForm> list) {
        super("Insufficient Quantity Exception");
        this.missing = list;
    }

}