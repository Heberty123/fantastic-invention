package br.com.Loja.controllers;

import br.com.Loja.dtos.AddressDTO;
import br.com.Loja.forms.AddressForm;
import br.com.Loja.models.Address;
import br.com.Loja.models.Customer;
import br.com.Loja.repositories.AddressRepository;
import br.com.Loja.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerService customerService;


    @PostMapping("/create/{customerId}")
    public ResponseEntity<AddressDTO> create(@RequestBody AddressForm addressForm, @PathVariable Long customerId){

        Address address = addressForm.toAddress();
        Customer customer = customerService.getById(customerId);
        address.setCustomer(customer);
        AddressDTO dto = new AddressDTO(addressRepository.save(address));
        return ResponseEntity.created(null).body(dto);
    }

    @GetMapping("/all/{customerId}")
    public ResponseEntity<List<AddressDTO>> findAllByCustomer(@PathVariable Long customerId){
        List<Address> addresses = this.addressRepository.findAllByCustomerId(customerId);
        List<AddressDTO> addressesDTOS = addresses.stream().map(AddressDTO::new).toList();
        return new ResponseEntity<>(addressesDTOS, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<AddressDTO> update(@RequestBody AddressForm addressForm){

        Address address = addressForm.toAddress();
        address = this.addressRepository.save(address);
        return ResponseEntity.ok(new AddressDTO(address));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){

        this.addressRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
