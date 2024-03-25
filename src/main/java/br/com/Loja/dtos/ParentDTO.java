package br.com.Loja.dtos;

import br.com.Loja.models.Customer;

public record ParentDTO(
        Long id,
        String name,
        String cpf
) {
    public ParentDTO(Customer customer) {
        this(
                customer.getId(),
                customer.getName(),
                customer.getCpf()
        );
    }
}
