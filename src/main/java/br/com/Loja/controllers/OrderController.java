package br.com.Loja.controllers;

import br.com.Loja.dto.OrderDTO;
import br.com.Loja.form.OrderForm;
import br.com.Loja.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService service;


    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Set<OrderDTO>> find(@PathVariable Long customerId){

        Set<OrderDTO> dtos =
                this.service.findAllByCustomerId(customerId);

        if(dtos.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/save")
    public ResponseEntity<OrderDTO> create(@RequestBody OrderForm orderForm){

        OrderDTO dto = this.service.create(orderForm);

        return ResponseEntity.ok(dto);
    }

}
