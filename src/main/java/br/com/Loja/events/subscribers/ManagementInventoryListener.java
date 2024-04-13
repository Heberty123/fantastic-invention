package br.com.Loja.events.subscribers;

import br.com.Loja.events.InventoriesSettedEvent;
import br.com.Loja.events.InventorySettedEvent;
import br.com.Loja.events.RegisteredOrderEvent;
import br.com.Loja.models.Product;
import br.com.Loja.models.ProductOrders;
import br.com.Loja.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ManagementInventoryListener {

    @Autowired
    private ProductRepository repository;


    @EventListener
    public void registeredOrder(RegisteredOrderEvent event) {
        List<Product> changed = new ArrayList<>();
        for (ProductOrders p: event.getOrder().getProductOrders()) {
            repository.setQuantityById(p.getQuantity(), p.getProduct().getId());
            changed.add(p.getProduct());
        }
        changeInventories(new InventoriesSettedEvent(this, changed));
    }

    @Async
    @EventListener
    public void changeInventory(InventorySettedEvent event) {
        Product product = event.getProduct();

    }

    @Async
    @EventListener
    public void changeInventories(InventoriesSettedEvent event) {
        List<Product> products = event.getProducts();

    }

}
