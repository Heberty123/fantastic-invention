package br.com.Loja.controllers;

import br.com.Loja.dtos.ProductTypeDTO;
import br.com.Loja.dtos.ProductTypeDashboard;
import br.com.Loja.forms.ProductTypeForm;
import br.com.Loja.models.ProductType;
import br.com.Loja.repositories.ProductTypeRepository;
import br.com.Loja.services.ProductTypeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService service;

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

    @PostMapping
    public ResponseEntity<ProductTypeDTO> save(@RequestBody ProductTypeForm productTypeForm){
        ProductType productType = productTypeForm.toProductType();
        ProductTypeDTO dto = new ProductTypeDTO(this.repository.save(productType));
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<ProductType> opt = this.repository.findById(id);
        if(opt.isPresent() && !opt.get().getProducts().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        this.repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<List<ProductTypeDashboard>> getDashboard(
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate){
        List<ProductTypeDashboard> dtos = this.repository.getDashboard(startDate, endDate);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
