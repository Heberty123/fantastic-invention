package br.com.Loja.controllers;

import br.com.Loja.dtos.BrandDTO;
import br.com.Loja.dtos.BrandFinalValueDTO;
import br.com.Loja.forms.BrandForm;
import br.com.Loja.models.Brand;
import br.com.Loja.repositories.BrandRepository;
import br.com.Loja.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("v1/api/brand")
public class BrandController {

    @Autowired
    private BrandService service;

    @Autowired
    private BrandRepository repository;

    @GetMapping
    public ResponseEntity<List<BrandDTO>> findAll(){
        List<Brand> brands = this.repository.findAll();

        List<BrandDTO> dtos = brands.stream()
                .map(BrandDTO::new)
                .toList();
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BrandDTO> save(@RequestBody BrandForm brandForm){

        Brand brand = brandForm.toBrand();
        BrandDTO dto = new BrandDTO(this.repository.save(brand));
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/brandFinalValue")
    public ResponseEntity<List<BrandFinalValueDTO>> brandFinalValue(
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<BrandFinalValueDTO> dtos = service.findAllFinalValue(startDate, endDate);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
