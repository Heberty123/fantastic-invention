package br.com.Loja.controllers;

import br.com.Loja.dto.AddressDTO;
import br.com.Loja.form.AddressForm;
import br.com.Loja.models.Address;
import br.com.Loja.models.Customer;
import br.com.Loja.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/create/{customerId}")
    public ResponseEntity<AddressDTO> create(/*@RequestBody OrderForm orderForm,*/ @PathVariable Long customerId){


        return ResponseEntity.created(null).body(null);
    }
}
