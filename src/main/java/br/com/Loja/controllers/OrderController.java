package br.com.Loja.controllers;

import br.com.Loja.dtos.OrderDTO;
import br.com.Loja.dtos.SimpleDataDashboardDTO;
import br.com.Loja.forms.OrderForm;
import br.com.Loja.repositories.OrderRepository;
import br.com.Loja.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private OrderRepository repository;


    @GetMapping("/customer/{customerId}/{paid}")
    public ResponseEntity<Set<OrderDTO>> find(@PathVariable Long customerId, @PathVariable boolean paid){
        return ResponseEntity.ok(service.findAllByCustomerId(customerId, paid));
    }

    @PostMapping("/save")
    public ResponseEntity<OrderDTO> create(@RequestBody OrderForm orderForm){

        OrderDTO dto = this.service.create(orderForm);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<List<SimpleDataDashboardDTO>> dashboard(
            @RequestParam(name = "year") int year){
        List<SimpleDataDashboardDTO> dtos = repository.findSalesDataByCurrentYear(year);
        return ResponseEntity.ok(dtos);
    }

}
