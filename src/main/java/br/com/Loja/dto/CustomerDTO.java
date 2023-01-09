package br.com.Loja.dto;

import br.com.Loja.models.Customer;
import lombok.Data;

@Data
public class CustomerDTO {

    private Long id;
    private String name;
    private String cpf;
    private String cellphone1;
    private String cellphone2;
    private String telephone1;
    private String telephone2;

    public CustomerDTO(){}

    public CustomerDTO(String name, String cpf, String cellphone1, String cellphone2, String telephone1, String telephone2) {
        this.name = name;
        this.cpf = cpf;
        this.cellphone1 = cellphone1;
        this.cellphone2 = cellphone2;
        this.telephone1 = telephone1;
        this.telephone2 = telephone2;
    }

    public CustomerDTO(Customer customer) {
        name = customer.getName();
        cpf = customer.getCpf();
        cellphone1 = customer.getCellphone1();
        cellphone2 = customer.getCellphone2();
        telephone1 = customer.getTelephone1();
        telephone2 = customer.getTelephone2();
    }
}
