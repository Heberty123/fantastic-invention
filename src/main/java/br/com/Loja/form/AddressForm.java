package br.com.Loja.form;

import br.com.Loja.models.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressForm {
    private String street;

    private Integer number;

    private String neighborhood;

    private String city;

    private String uf;

    private String cep;

    private DeliveryTypeForm deliveryType;

    private String cellphone1;

    private String cellphone2;

    private String telephone1;

    private String telephone2;

    public AddressForm(String street, Integer number, String neighborhood,
                      String city, String uf, String cep, DeliveryTypeForm deliveryType,
                      String cellphone1, String cellphone2,
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

    public Address toAddress() {
        return new Address(
                street,
                number,
                neighborhood,
                city,
                uf,
                cep,
                deliveryType.toDeliveryType(),
                cellphone1,
                cellphone2,
                telephone1,
                telephone2
        );
    }
}
