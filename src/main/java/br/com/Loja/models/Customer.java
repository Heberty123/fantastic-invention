package br.com.Loja.models;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String cpf;
    private String cellphone1;
    private String cellphone2;
    private String telephone1;
    private String telephone2;

    public Customer(){}

    public Customer(String name, String cpf, String cellphone1, String cellphone2, String telephone1, String telephone2) {
        this.name = name;
        this.cpf = cpf;
        this.cellphone1 = cellphone1;
        this.cellphone2 = cellphone2;
        this.telephone1 = telephone1;
        this.telephone2 = telephone2;
    }
}
