package br.com.Loja.events;

import br.com.Loja.models.Order;
import org.springframework.context.ApplicationEvent;

public class RegisteredOrderEvent extends ApplicationEvent {
    private Order order;

    public RegisteredOrderEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
