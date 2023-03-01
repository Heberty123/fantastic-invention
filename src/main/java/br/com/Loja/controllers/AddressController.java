package br.com.Loja.controllers;

import br.com.Loja.dto.AddressDTO;
import br.com.Loja.dto.DeliveryTypeDTO;
import br.com.Loja.form.AddressForm;
import br.com.Loja.form.CustomerForm;
import br.com.Loja.models.Address;
import br.com.Loja.models.Customer;
import br.com.Loja.models.DeliveryType;
import br.com.Loja.repositories.AddressRepository;
import br.com.Loja.repositories.CustomerRepository;
import br.com.Loja.repositories.DeliveryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DeliveryTypeRepository deliveryTypeRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @PostMapping("/create/{customerId}")
    public ResponseEntity<AddressDTO> create(@RequestBody AddressForm addressForm, @PathVariable Long customerId){

        Address address = addressForm.toAddress();
        Optional<Customer> optional = customerRepository.findById(customerId);

        if(optional.isEmpty())
            return ResponseEntity.notFound().build();

        address.setCustomer(optional.get());
        AddressDTO dto = new AddressDTO(addressRepository.save(address));
        return ResponseEntity.created(null).body(dto);
    }

    @GetMapping("/all/{customerId}")
    public ResponseEntity<List<AddressDTO>> findAllByCustomer(@PathVariable Long customerId){
        List<Address> addresses = this.addressRepository.findAllByCustomerId(customerId);
        if(!addresses.isEmpty()){
            List<AddressDTO> addressesDTOS = addresses.stream().map(AddressDTO::new).toList();
            return new ResponseEntity<>(addressesDTOS, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all/deliveryType")
    public ResponseEntity<List<DeliveryTypeDTO>> findAllDeliveryType(){
        List<DeliveryType> deliverys = this.deliveryTypeRepository.findAll();

        if(deliverys.isEmpty())
            return ResponseEntity.notFound().build();

        List<DeliveryTypeDTO> dtos = deliverys.stream()
                .map(DeliveryTypeDTO::new)
                .toList();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){

        this.addressRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
