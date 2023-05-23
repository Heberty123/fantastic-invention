package br.com.Loja.dto;

import br.com.Loja.models.Customer;
import br.com.Loja.models.Order;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Data
public class CustomerDTO {

    private Long id;

    private String name;

    private String cpf;

    private List<Order> orders = new ArrayList<Order>();

    public CustomerDTO(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
        this.cpf = customer.getCpf();
        this.orders = customer.getOrders();
    }
}
