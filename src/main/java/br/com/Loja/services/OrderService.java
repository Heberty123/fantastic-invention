package br.com.Loja.services;

import br.com.Loja.dto.OrderDTO;
import br.com.Loja.form.OrderForm;
import br.com.Loja.models.Customer;
import br.com.Loja.models.Order;
import br.com.Loja.models.Product;
import br.com.Loja.models.ProductsOrders;
import br.com.Loja.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;


    public Set<OrderDTO> findAllByCustomerId(Long customerId){
        Set<Order> orders = repository.findAllByCustomerId(customerId);
        Set<OrderDTO> dtos = orders
                .stream()
                .map(OrderDTO::new)
                .collect(Collectors.toSet());
        return dtos;
    }


    public OrderDTO create(OrderForm orderForm){

        Order order = repository.save(new Order());
        order.setStatus(orderForm.getStatus());
        order.setCustomer(new Customer(orderForm.getCustomerId()));
        order.setGrossAmount(orderForm.getGrossAmount());
        order.setNetAmount(orderForm.getNetAmount());
        order.setDiscounts(orderForm.getDiscounts());
        order.setProductsOrders(
                orderForm.getProductsOrders()
                    .stream()
                    .map(v ->
                            new ProductsOrders(
                                order,
                                new Product(v.getProductId()),
                                v.getQuantity(),
                                v.getDiscounts(),
                                v.getIsRefund()
                    ))
                    .collect(Collectors.toSet())
        );
        order.setCreatedAt(LocalDateTime.now());

        OrderDTO dto = new OrderDTO(this.repository.save(order));

        return dto;
    }
}
