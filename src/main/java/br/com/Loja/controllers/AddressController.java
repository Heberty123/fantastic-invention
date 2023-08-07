package br.com.Loja.controllers;

import br.com.Loja.dto.AddressDTO;
import br.com.Loja.form.AddressForm;
import br.com.Loja.models.Address;
import br.com.Loja.models.Customer;
import br.com.Loja.repositories.AddressRepository;
import br.com.Loja.repositories.CustomerRepository;
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
