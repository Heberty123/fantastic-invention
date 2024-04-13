package br.com.Loja.events;

import br.com.Loja.models.Product;
import org.springframework.context.ApplicationEvent;

public class InventorySettedEvent extends ApplicationEvent {
    private Product product;

    public InventorySettedEvent(Object source, Product product) {
        super(source);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}