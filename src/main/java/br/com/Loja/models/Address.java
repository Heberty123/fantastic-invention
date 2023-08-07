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
    @JoinColumn(updatable = false)
    private Customer customer;


    public Address(Long id, String street, Integer number, String neighborhood, String city, String uf,
                   String cep, DeliveryType deliveryType, String cellphone1, String cellphone2,
                   String telephone1, String telephone2) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", uf='" + uf + '\'' +
                ", cep='" + cep + '\'' +
                ", deliveryType=" + deliveryType +
                ", cellphone1='" + cellphone1 + '\'' +
                ", cellphone2='" + cellphone2 + '\'' +
                ", telephone1='" + telephone1 + '\'' +
                ", telephone2='" + telephone2 + '\'' +
                ", customer=" + customer +
                '}';
    }
}
