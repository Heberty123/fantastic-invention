package br.com.Loja.controllers;

import br.com.Loja.dto.ProductDTO;
import br.com.Loja.dto.SimpleProductDTO;
import br.com.Loja.form.ProductForm;
import br.com.Loja.models.Product;
import br.com.Loja.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){

        Optional<Product> optional = this.repository.findById(id);
        if(optional.isEmpty())
            return ResponseEntity.notFound().build();

        ProductDTO dto = new ProductDTO(optional.get());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SimpleProductDTO>> findAll(){
        List<Product> products = this.repository.findAll();
        List<SimpleProductDTO> dtos = products.stream()
                .map(SimpleProductDTO::new)
                .toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductForm productForm){
        ProductDTO dto = new ProductDTO(repository.save(productForm.toProduct()));
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/barcode/{barcode}")
    public ResponseEntity<ProductDTO> findByBarcode(@PathVariable String barcode){
        Product product = repository.findByBarcode(barcode);
        if(product == null)
            return ResponseEntity.notFound().build();

        ProductDTO dto = new ProductDTO(product);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
