package br.com.Loja.dtos;

import br.com.Loja.models.Customer;


public record CustomerDTO (
        Long id,
        String name,
        String cpf,
        ParentDTO parent
) {


    public CustomerDTO(Customer customer){
        this(
        customer.getId(),
        customer.getName(),
        customer.getCpf(),
        customer.getParentCustomer() == null ?
                null : new ParentDTO(customer.getParentCustomer()));
    }
}
