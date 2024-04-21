package br.com.Loja.events.subscribers;

import br.com.Loja.dtos.ProductStockStatus;
import br.com.Loja.events.InventoriesSettedEvent;
import br.com.Loja.events.RegisteredOrderEvent;
import br.com.Loja.models.Product;
import br.com.Loja.models.ProductOrders;
import br.com.Loja.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ManagementInventoryListener {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @EventListener
    public void registeredOrder(RegisteredOrderEvent event) {
        List<Product> changed = new ArrayList<>();
        for (ProductOrders p: event.getOrder().getProductOrders()) {
            repository.decrementQuantityById(p.getQuantity(), p.getProduct().getId());
            changed.add(p.getProduct());
        }
        changeInventories(new InventoriesSettedEvent(this));
    }

    @Async
    @EventListener
    public void changeInventories(InventoriesSettedEvent event) {
        List<ProductStockStatus> stocks = repository.findAllStockStatus();
        messagingTemplate.convertAndSend("/topic/stock", stocks);
    }
}
