package br.com.Loja.repositories;

import br.com.Loja.models.DeliveryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTypeRepository extends JpaRepository<DeliveryType, Long> {
}
