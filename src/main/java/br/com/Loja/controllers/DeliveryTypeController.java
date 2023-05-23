package br.com.Loja.controllers;

import br.com.Loja.dto.DeliveryTypeDTO;
import br.com.Loja.form.DeliveryTypeForm;
import br.com.Loja.models.DeliveryType;
import br.com.Loja.repositories.DeliveryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/deliveryType")
public class DeliveryTypeController {

    @Autowired
    private DeliveryTypeRepository deliveryTypeRepository;


    @PostMapping("/save")
    public ResponseEntity<DeliveryTypeDTO> save(@RequestBody DeliveryTypeForm deliveryTypeForm){

        DeliveryType newDeliveryType = this.deliveryTypeRepository
                .save(deliveryTypeForm.toDeliveryType());

        DeliveryTypeDTO dto = new DeliveryTypeDTO(newDeliveryType);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @GetMapping("/all/deliveryType")
    public ResponseEntity<List<DeliveryTypeDTO>> findAllDeliveryType(){
        List<DeliveryType> deliverys = this.deliveryTypeRepository.findAll();

        if(deliverys.isEmpty())
            return ResponseEntity.notFound().build();

        List<DeliveryTypeDTO> dtos = deliverys.stream()
                .map(DeliveryTypeDTO::new)
                .toList();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
