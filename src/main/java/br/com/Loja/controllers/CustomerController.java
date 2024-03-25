package br.com.Loja.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fge.jsonpatch.JsonPatchException;

import br.com.Loja.dtos.CustomerDTO;
import br.com.Loja.forms.CustomerForm;
import br.com.Loja.repositories.CustomerRepository;
import br.com.Loja.services.CustomerService;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerService service;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll(){

        List<CustomerDTO> dtos = service.findAll()
            .stream()
            .map(CustomerDTO::new)
            .toList();
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Long id){
        CustomerDTO dto =
            new CustomerDTO(service.getById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Boolean> existByCPF(@PathVariable String cpf){
        if(this.repository.existsByCpf(cpf))
            return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> save(@RequestBody CustomerForm form){
        return new ResponseEntity<>(
            new CustomerDTO(service.save(form)),
            HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerForm form){
        return new ResponseEntity<>(
            new CustomerDTO(service.save(form)),
            HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/dependents")
    public ResponseEntity<CustomerDTO> saveDependent(
        @RequestBody CustomerForm form,
        @PathVariable Long id){

        CustomerDTO dto = 
            new CustomerDTO(service.saveDependent(form, id));
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/dependents")
    public ResponseEntity<List<CustomerDTO>> findAllDependents(@PathVariable Long id){
        List<CustomerDTO> dtos = 
            service.findAllDependents(id)
            .stream()
            .map(CustomerDTO::new)
            .toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @PatchMapping("/deleteAllByIds")
    public ResponseEntity<Void> deleteList(
        @RequestBody Map<String, List<Long>> map) throws JsonPatchException{
        service.deleteList(map.get("ids"));
        return ResponseEntity.noContent().build();
    }

}
