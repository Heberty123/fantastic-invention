package br.com.Loja.controllers;

import br.com.Loja.dto.OrderDTO;
import br.com.Loja.form.OrderForm;
import br.com.Loja.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> create(@RequestBody OrderForm orderForm){

        OrderDTO dto = this.service.create(orderForm);

        return ResponseEntity.ok(dto);
    }
}
