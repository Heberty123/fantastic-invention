package br.com.Loja.repositories;

import br.com.Loja.models.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
}
