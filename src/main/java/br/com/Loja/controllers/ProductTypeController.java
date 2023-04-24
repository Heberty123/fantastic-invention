package br.com.Loja.controllers;

import br.com.Loja.dto.ProductTypeDTO;
import br.com.Loja.form.ProductForm;
import br.com.Loja.form.ProductTypeForm;
import br.com.Loja.models.ProductType;
import br.com.Loja.repositories.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeRepository repository;

    @GetMapping("/all")
    public ResponseEntity<List<ProductTypeDTO>> findAll(){
        List<ProductType> chips = this.repository.findAll();

        if(chips.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        List<ProductTypeDTO> dtos = chips.stream()
                .map(ProductTypeDTO::new)
                .toList();
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ProductTypeDTO> create(@RequestBody ProductTypeForm productTypeForm){
        ProductType productType = productTypeForm.toProductType();

        ProductTypeDTO dto = new ProductTypeDTO(this.repository.save(productType));

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        this.repository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
