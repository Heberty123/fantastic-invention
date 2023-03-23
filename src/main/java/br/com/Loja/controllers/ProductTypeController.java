package br.com.Loja.controllers;

import br.com.Loja.dto.ProductTypeDTO;
import br.com.Loja.models.ProductType;
import br.com.Loja.repositories.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @GetMapping("/all")
    public ResponseEntity<List<ProductTypeDTO>> findAll(){
        List<ProductType> chips = this.productTypeRepository.findAll();

        if(chips.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        List<ProductTypeDTO> dtos = chips.stream()
                .map(ProductTypeDTO::new)
                .toList();
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }
}
