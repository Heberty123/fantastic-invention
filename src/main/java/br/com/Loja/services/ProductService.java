package br.com.Loja.services;

import br.com.Loja.dtos.ProductFinalValueDTO;
import br.com.Loja.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductFinalValueDTO> findAllFinalValue() {
        List<ProductFinalValueDTO> list =  repository.findAllFinalValue();
        System.out.println(list);
        return list;
    }
}
