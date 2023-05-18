package br.com.Loja.controllers;

import br.com.Loja.dto.CustomerDTO;
import br.com.Loja.form.CustomerForm;
import br.com.Loja.models.Customer;
import br.com.Loja.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDTO>> findAll(){
        List<Customer> customers = this.repository.findAll();

        if(customers.isEmpty())
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        List<CustomerDTO> dtos = customers.stream()
                .map(CustomerDTO::new)
                .toList();
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }


    @GetMapping("/exist/{cpf}")
    public ResponseEntity<Boolean> existByCPF(@PathVariable String cpf){
        if(this.repository.existsByCpf(cpf))
            return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(false, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerForm customerForm){
        CustomerDTO dto = new CustomerDTO(repository.save(customerForm.toCustomer()));
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerForm customerForm,
                                              @PathVariable Long customerId){
        Customer customer = customerForm.toCustomer();
        customer.setId(customerId);
        CustomerDTO dto = new CustomerDTO(repository.save(customer));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteById(@PathVariable Long customerId){
        this.repository.deleteById(customerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create/dependent/{customerId}")
    public ResponseEntity<CustomerDTO> createDependent(@RequestBody CustomerForm customerForm, @PathVariable Long customerId){
        Customer customer = this.repository.getReferenceById(customerId);

        Customer customerDependent = customerForm.toCustomer();
        customerDependent.setParentCustomer(customer);
        CustomerDTO dto = new CustomerDTO(this.repository.save(customerDependent));

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/all/dependentsCustomers/{customerId}")
    public ResponseEntity<List<CustomerDTO>> findAllDependentsCustomers(@PathVariable Long customerId){
        List<Customer> customer = this.repository.findAllByParentCustomerId(customerId);
        List<CustomerDTO> dtos = customer.stream()
                .map(CustomerDTO::new)
                .toList();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
