package br.com.Loja.controllers;

import br.com.Loja.dtos.AddressDTO;
import br.com.Loja.forms.AddressForm;
import br.com.Loja.models.Address;
import br.com.Loja.models.Customer;
import br.com.Loja.repositories.AddressRepository;
import br.com.Loja.services.AddressService;
import br.com.Loja.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("v1/api/customers")
public class AddressController {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private AddressService service;

    @Autowired
    private CustomerService customerService;


    @PostMapping("/{customerId}/addresses")
    public ResponseEntity<AddressDTO> create(@RequestBody AddressForm addressForm, @PathVariable Long customerId){

        Address address = addressForm.toAddress();
        address = service.create(address, customerId);
        AddressDTO dto = new AddressDTO(address);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}/addresses")
    public ResponseEntity<List<AddressDTO>> findAllByCustomer(@PathVariable Long customerId){
        List<Address> addresses = this.repository.findAllByCustomerId(customerId);
        List<AddressDTO> dtos = addresses.stream().map(AddressDTO::new).toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @PutMapping("/addresses/{id}")
    public ResponseEntity<AddressDTO> update(@RequestBody AddressForm addressForm,
                                             @PathVariable Long customerId){

        Address address = addressForm.toAddress();
        address = this.service.update(address, customerId);
        return ResponseEntity.ok(new AddressDTO(address));
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        this.repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
