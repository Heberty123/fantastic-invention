package br.com.Loja.services;

import br.com.Loja.dto.OrderDTO;
import br.com.Loja.form.OrderForm;
import br.com.Loja.models.*;
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


    public Set<OrderDTO> findAllByCustomerId(Long customerId, boolean paid){
        Set<Order> orders = repository.findAllByCustomerId(customerId, paid);
        Set<OrderDTO> dtos = orders
                .stream()
                .map(OrderDTO::new)
                .collect(Collectors.toSet());
        return dtos;
    }


    public OrderDTO create(OrderForm orderForm){

        Order order = repository.save(new Order());
        order.setPaid(orderForm.isPaid());
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
                                new Product(v.getProduct().getId()),
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
                                            LocalDateTime.now()
                                            :
                                            v.getPayedAt(),
                                    v.getAmountPayed(),
                                    v.getPaid(),
                                    order
                            ))
                    .collect(Collectors.toList())
        );
        order.setCreatedAt(LocalDateTime.now());
        System.out.println(order.getPayments());

        OrderDTO dto = new OrderDTO(this.repository.save(order));

        return dto;
    }
}
