package br.com.Loja.form;

import br.com.Loja.models.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerForm {

    private Long id;
    private String name;

    private String cpf;


    public CustomerForm(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public Customer toCustomer() {
        return new Customer(id, name, cpf);
    }

}
