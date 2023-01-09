package br.com.Loja.controllers;

import br.com.Loja.models.Customer;
import br.com.Loja.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> findAll(){
        List<Customer> customers = this.repository.findAll();

        if(customers.isEmpty())
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(customers,HttpStatus.OK);
    }


    @GetMapping("/exist/{cpf}")
    public ResponseEntity<Boolean> existByCPF(@PathVariable String cpf){
        if(this.repository.existsByCpf(cpf))
            return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
