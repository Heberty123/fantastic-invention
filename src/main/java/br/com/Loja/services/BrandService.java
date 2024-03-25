package br.com.Loja.services;

import br.com.Loja.dtos.BrandFinalValueDTO;
import br.com.Loja.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository repository;

    public List<BrandFinalValueDTO> findAllFinalValue() {
        return repository.findAllFinalValue();
    }

}
