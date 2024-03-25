package br.com.Loja.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.github.fge.jsonpatch.JsonPatchException;

import br.com.Loja.exception.EntityNotFoundException;
import br.com.Loja.forms.CustomerForm;
import br.com.Loja.models.Customer;
import br.com.Loja.repositories.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository repository;

    @GetMapping
    public List<Customer> findAll(){
        return this.repository.findAll();
    }

    public Customer getById(Long id){
        return this.repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer", id));
    }

    public Customer save(CustomerForm form){
        Customer customer = new Customer(form);
        return repository.save(customer);
    }

    public Customer update(CustomerForm form){
        Customer customer = new Customer(form);
        return repository.save(customer);
    }

    public void deleteById(Long id){
        this.repository.deleteById(id);
    }

    public Customer saveDependent(CustomerForm form, Long id){
        Customer customer = getById(id);
        Customer dependent = new Customer(form);
        dependent.setParentCustomer(customer);
        return this.repository.save(dependent);
    }

    public List<Customer> findAllDependents(@PathVariable Long id){
        return this.repository.findAllByParentCustomerId(id);
    }

    public void deleteList(List<Long> ids) throws JsonPatchException{
        this.repository.deleteAllById(ids);
    }
}
