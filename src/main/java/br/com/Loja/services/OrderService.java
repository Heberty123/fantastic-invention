package br.com.Loja.services;

import br.com.Loja.dto.OrderDTO;
import br.com.Loja.form.OrderForm;
import br.com.Loja.models.Customer;
import br.com.Loja.models.Order;
import br.com.Loja.models.Product;
import br.com.Loja.models.ProductsOrders;
import br.com.Loja.repositories.CustomerRepository;
import br.com.Loja.repositories.OrderRepository;
import br.com.Loja.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private CustomerRepository customerR;

    @Autowired
    private ProductRepository productR;


    public OrderDTO create(OrderForm orderForm){

        Order order = repository.save(new Order());
        order.setStatus(orderForm.getStatus());
        order.setCustomer(new Customer(orderForm.getCustomerId()));
        order.setProductsOrders(
                orderForm.getProductsOrders()
                    .stream()
                    .map(v ->
                            new ProductsOrders(
                                order,
                                new Product(v.getProductId()),
                                v.getQuantity()
                    ))
                    .collect(Collectors.toSet())
        );

        OrderDTO dto = new OrderDTO(this.repository.save(order));

        return dto;
    }
}
