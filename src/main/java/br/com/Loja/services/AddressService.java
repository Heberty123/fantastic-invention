package br.com.Loja.services;

import br.com.Loja.models.Address;
import br.com.Loja.models.Customer;
import br.com.Loja.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    private CustomerService customerService;

    public Address create(Address address, Long customerId){
        Customer customer = customerService.getReferenceById(customerId);
        address.setCustomer(customer);
        return repository.save(address);
    }

    public Address update(Address address, Long customerId){
        address.setId(customerId);
        return repository.save(address);
    }

}
