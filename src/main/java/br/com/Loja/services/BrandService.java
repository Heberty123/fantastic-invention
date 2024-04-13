package br.com.Loja.services;

import br.com.Loja.dtos.BrandFinalValueDTO;
import br.com.Loja.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BrandService {

    @Autowired
    private BrandRepository repository;

    public List<BrandFinalValueDTO> findAllFinalValue(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findAllFinalValue(startDate, endDate);
    }

}
