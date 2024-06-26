package br.com.Loja.controllers;

import br.com.Loja.dtos.ProductDTO;
import br.com.Loja.dtos.ProductDashboard;
import br.com.Loja.dtos.ProductStockStatus;
import br.com.Loja.dtos.SimpleProductDTO;
import br.com.Loja.events.InventoriesSettedEvent;
import br.com.Loja.exception.EntityNotFoundException;
import br.com.Loja.forms.ProductForm;
import br.com.Loja.forms.ProductOrdersForm;
import br.com.Loja.forms.ProductStockForm;
import br.com.Loja.models.Product;
import br.com.Loja.repositories.ProductRepository;
import br.com.Loja.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        Product product = this.repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product", id));

        return new ResponseEntity<>(new ProductDTO(product), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SimpleProductDTO>> findAll(){
        List<Product> products = this.repository.findAll();
        List<SimpleProductDTO> dtos = products.stream()
                .map(SimpleProductDTO::new)
                .toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductForm productForm){
        ProductDTO dto = new ProductDTO(repository.save(productForm.toProduct()));
        applicationEventPublisher.publishEvent(new InventoriesSettedEvent(this));
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductForm productForm){
        ProductDTO dto = new ProductDTO(repository.save(productForm.toProduct()));
        applicationEventPublisher.publishEvent(new InventoriesSettedEvent(this));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/barcode/{barcode}")
    public ResponseEntity<ProductDTO> findByBarcode(@PathVariable String barcode){
        Product product = repository.findByBarcode(barcode);
        if(product == null)
            return ResponseEntity.notFound().build();

        ProductDTO dto = new ProductDTO(product);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/valid-inventory")
    public ResponseEntity<Void> validInventory(@RequestBody Set<ProductOrdersForm> products) {
        service.validInventory(products);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/productFinalValue")
    public ResponseEntity<List<ProductDashboard>> findAllFinalValue(
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        return new ResponseEntity<>(service.findAllFinalValue(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/stock-status")
    public ResponseEntity<List<ProductStockStatus>> findAllStockStatus(){
        return new ResponseEntity<>(repository.findAllStockStatus(), HttpStatus.OK);
    }

    @PatchMapping("/stocks")
    public ResponseEntity<Void> updateStock(@RequestBody List<ProductStockForm> forms){
        service.updateStocks(forms);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        repository.deleteById(id);
        applicationEventPublisher.publishEvent(new InventoriesSettedEvent(this));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
