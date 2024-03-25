package br.com.Loja.forms;

import br.com.Loja.models.Customer;

public record CustomerForm (
        Long id,
        String name,
        String cpf
) {
    public CustomerForm(Customer customer) {
        this(
                customer.getId(),
                customer.getName(),
                customer.getCpf()
        );
    }
}
