package br.com.Loja.controllers;

import br.com.Loja.dto.CustomerDTO;
import br.com.Loja.dto.SimpleCustomerDTO;
import br.com.Loja.form.CustomerForm;
import br.com.Loja.models.Customer;
import br.com.Loja.repositories.CustomerRepository;
import br.com.Loja.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerService service;

    @GetMapping("/all")
    public ResponseEntity<List<SimpleCustomerDTO>> findAll(){
        List<Customer> customers = this.repository.findAll();

        if(customers.isEmpty())
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        List<SimpleCustomerDTO> dtos = customers.stream()
                .map(SimpleCustomerDTO::new)
                .toList();
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Long id){
        Optional<Customer> optional = this.repository.findById(id);
        if(optional.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        CustomerDTO dto = new CustomerDTO(optional.get());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/exist/{cpf}")
    public ResponseEntity<Boolean> existByCPF(@PathVariable String cpf){
        if(this.repository.existsByCpf(cpf))
            return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SimpleCustomerDTO> create(@RequestBody CustomerForm customerForm){
        SimpleCustomerDTO dto = new SimpleCustomerDTO(repository.save(customerForm.toCustomer()));
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SimpleCustomerDTO> update(@RequestBody CustomerForm customerForm){
        Customer customer = customerForm.toCustomer();
        SimpleCustomerDTO dto = new SimpleCustomerDTO(repository.save(customer));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        this.repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create/dependent/{id}")
    public ResponseEntity<SimpleCustomerDTO> createDependent(@RequestBody CustomerForm customerForm, @PathVariable Long id){
        Customer customer = this.repository.getReferenceById(id);

        Customer customerDependent = customerForm.toCustomer();
        customerDependent.setParentCustomer(customer);
        SimpleCustomerDTO dto = new SimpleCustomerDTO(this.repository.save(customerDependent));

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/all/dependentsCustomers/{id}")
    public ResponseEntity<List<SimpleCustomerDTO>> findAllDependentsCustomers(@PathVariable Long id){
        List<Customer> customer = this.repository.findAllByParentCustomerId(id);
        List<SimpleCustomerDTO> dtos = customer.stream()
                .map(SimpleCustomerDTO::new)
                .toList();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
