package br.com.Loja.dto;

import br.com.Loja.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FullCustomerDTO {

    private Long id;

    private String name;

    private String cpf;

    private List<AddressDTO> addresses;

    private Set<CustomerDTO> dependents;

    private List<OrderDTO> orders;

    public FullCustomerDTO(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
        this.cpf = customer.getCpf();
        this.addresses = customer.getAddresses()
                .stream()
                .map(AddressDTO::new)
                .collect(Collectors.toList());
        this.dependents = customer.getDependents()
                .stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toSet());
        this.orders = customer.getOrders()
                .stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }
}
