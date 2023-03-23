package br.com.Loja.controllers;

import br.com.Loja.dto.BrandDTO;
import br.com.Loja.form.BrandForm;
import br.com.Loja.models.Brand;
import br.com.Loja.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/brand")
public class BrandController {

    @Autowired
    private BrandRepository repository;

    @GetMapping("/all")
    public ResponseEntity<List<BrandDTO>> findAll(){
        List<Brand> brands = this.repository.findAll();

        if(brands.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        List<BrandDTO> dtos = brands.stream()
                .map(BrandDTO::new)
                .toList();
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BrandDTO> create(@RequestBody BrandForm brandForm){

        Brand brand = brandForm.toBrand();
        BrandDTO dto = new BrandDTO(this.repository.save(brand));
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
