package br.com.Loja.dtos;

import br.com.Loja.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id;

    private String street;

    private Integer number;

    private String neighborhood;

    private String city;

    private String uf;

    private String cep;

    private DeliveryTypeDTO deliveryType;

    private String cellphone1;

    private String cellphone2;

    private String telephone1;

    private String telephone2;

    public AddressDTO(Address address) {
        id = address.getId();
        street = address.getStreet();
        number = address.getNumber();
        neighborhood = address.getNeighborhood();
        city = address.getCity();
        uf = address.getUf();
        cep = address.getCep();
        deliveryType = new DeliveryTypeDTO(address.getDeliveryType());
        cellphone1 = address.getCellphone1();
        cellphone2 = address.getCellphone2();
        telephone1 = address.getTelephone1();
        telephone2 = address.getTelephone2();
    }
}
