package br.com.Loja.dto;

import br.com.Loja.models.Customer;
import lombok.Data;

@Data
public class SimpleCustomerDTO {

    private Long id;
    private String name;
    private String cpf;


    public SimpleCustomerDTO(){}

    public SimpleCustomerDTO(Long id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }

    public SimpleCustomerDTO(Customer customer) {
        id = customer.getId();
        name = customer.getName();
        cpf = customer.getCpf();
    }
}
