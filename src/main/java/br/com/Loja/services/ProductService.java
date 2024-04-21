package br.com.Loja.services;

import br.com.Loja.dtos.ProductDashboard;
import br.com.Loja.events.InventoriesSettedEvent;
import br.com.Loja.events.RegisteredOrderEvent;
import br.com.Loja.exception.InsufficientQuantityException;
import br.com.Loja.forms.OrderForm;
import br.com.Loja.forms.ProductOrdersForm;
import br.com.Loja.forms.ProductStockForm;
import br.com.Loja.forms.SimpleProductForm;
import br.com.Loja.models.Order;
import br.com.Loja.models.Product;
import br.com.Loja.models.ProductOrders;
import br.com.Loja.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    public List<ProductDashboard> findAllFinalValue(
            LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findAllFinalValue(startDate, endDate);
    }

    public void validInventory(Set<ProductOrdersForm> products) {
        List<SimpleProductForm> missing = products.stream()
                .filter(p -> repository.getQuantityById(p.getProduct().getId()) < p.getQuantity())
                .map(ProductOrdersForm::getProduct)
                .toList();

        if(!missing.isEmpty())
            throw new InsufficientQuantityException(missing);
    }


    @Transactional
    public void updateStocks(List<ProductStockForm> forms) {
        for(ProductStockForm form : forms)
            this.repository.setStockById(form.quantity(), form.id());
        applicationEventPublisher.publishEvent(new InventoriesSettedEvent(this));
    }
}
