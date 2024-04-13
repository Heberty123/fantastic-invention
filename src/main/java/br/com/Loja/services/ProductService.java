package br.com.Loja.services;

import br.com.Loja.dtos.ProductDashboard;
import br.com.Loja.exception.InsufficientQuantityException;
import br.com.Loja.forms.OrderForm;
import br.com.Loja.forms.ProductOrdersForm;
import br.com.Loja.forms.SimpleProductForm;
import br.com.Loja.models.Order;
import br.com.Loja.models.ProductOrders;
import br.com.Loja.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
