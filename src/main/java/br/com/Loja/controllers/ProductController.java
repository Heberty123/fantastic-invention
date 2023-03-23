package br.com.Loja.controllers;

import br.com.Loja.dto.ProductDTO;
import br.com.Loja.dto.ProductTypeDTO;
import br.com.Loja.form.ProductForm;
import br.com.Loja.models.ProductType;
import br.com.Loja.repositories.ProductRepository;
import br.com.Loja.repositories.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;


    @PostMapping("/create")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductForm productForm){
        ProductDTO dto = new ProductDTO(repository.save(productForm.toProduct()));
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
