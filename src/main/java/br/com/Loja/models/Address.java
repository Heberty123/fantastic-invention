package br.com.Loja.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private Integer number;

    private String neighborhood;

    private String city;

    private String uf;

    private String cep;

    @ManyToOne
    private DeliveryType deliveryType;

    private String cellphone1;

    private String cellphone2;

    private String telephone1;

    private String telephone2;

    @ManyToOne
    private Customer customer;


    public Address(String street, Integer number, String neighborhood, String city, String uf,
                   String cep, DeliveryType deliveryType, String cellphone1, String cellphone2,
                   String telephone1, String telephone2) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.uf = uf;
        this.cep = cep;
        this.deliveryType = deliveryType;
        this.cellphone1 = cellphone1;
        this.cellphone2 = cellphone2;
        this.telephone1 = telephone1;
        this.telephone2 = telephone2;
    }
}
