package br.com.Loja.services;

import br.com.Loja.dtos.OrderDTO;
import br.com.Loja.events.RegisteredOrderEvent;
import br.com.Loja.exception.EntityNotFoundException;
import br.com.Loja.forms.OrderForm;
import br.com.Loja.models.*;
import br.com.Loja.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public Order getById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order", id));
    }


    public Set<OrderDTO> findAllByCustomerId(Long customerId, boolean paid){
        Set<Order> orders = repository.findAllByCustomerId(customerId, paid);
        Set<OrderDTO> dtos = orders
                .stream()
                .map(OrderDTO::new)
                .collect(Collectors.toSet());
        return dtos;
    }


    public OrderDTO create(OrderForm orderForm){

        productService.validInventory(orderForm.getProductOrders());
        Order order = repository.save(new Order());
        order.setPaid(orderForm.isPaid());
        order.setCustomer(new Customer(orderForm.getCustomerId()));
        order.setGrossAmount(orderForm.getGrossAmount());
        order.setNetAmount(orderForm.getNetAmount());
        order.setDiscounts(orderForm.getDiscounts());
        order.setProductOrders(
                orderForm.getProductOrders()
                    .stream()
                    .map(v ->
                            new ProductOrders(
                                order,
                                new Product(v.getProduct().getId()),
                                v.getNetAmount(),
                                v.getGrossAmount(),
                                v.getQuantity(),
                                v.getDiscounts(),
                                v.getIsRefund()
                    ))
                    .collect(Collectors.toSet())
        );
        order.setPayments(
                orderForm.getPayments()
                    .stream()
                    .map(v ->
                            new Payment(
                                    null,
                                    v.getPaymentDate(),
                                    v.getAmount(),
                                    v.getPaymentType() != null ?
                                            v.getPaymentType().toPaymentType()
                                            :
                                            null,
                                    v.getAmountPayed() != null ?
                                            Calendar.getInstance()
                                            :
                                            v.getPayedAt(),
                                    v.getAmountPayed(),
                                    v.getPaid(),
                                    order
                            ))
                    .collect(Collectors.toList())
        );
        order.setCreatedAt(LocalDateTime.now());
        Order persisted = this.repository.save(order);
        applicationEventPublisher.publishEvent(new RegisteredOrderEvent(this, persisted));
        return new OrderDTO(persisted);
    }

    public void validOrderPaymentById(Long id) {
        Order order = getById(id);
        if(order.getPayments().stream().allMatch(Payment::getPaid)) {
            order.setPaid(true);
            repository.save(order);
        }
    }
}
