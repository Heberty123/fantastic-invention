package br.com.Loja.services;

import br.com.Loja.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public String teste(){
        return "Teste";
    }
}
