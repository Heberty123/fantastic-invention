package br.com.Loja.events;

import br.com.Loja.models.Product;
import org.springframework.context.ApplicationEvent;
import java.util.List;

public class InventoriesSettedEvent extends ApplicationEvent {
    private List<Product> products;

    public InventoriesSettedEvent(Object source, List<Product> products) {
        super(source);
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}