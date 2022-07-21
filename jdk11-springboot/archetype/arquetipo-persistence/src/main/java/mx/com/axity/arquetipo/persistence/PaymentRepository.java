package mx.com.axity.arquetipo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.axity.arquetipo.model.CustomerDO;
import mx.com.axity.arquetipo.model.PaymentDO;
import mx.com.axity.arquetipo.model.PaymentId;

/**
 * Interface de persistencia de {@link mx.com.axity.arquetipo.model.PaymentDO}
 * 
 * @author guillermo.segura@axity.com
 */
@Repository
public interface PaymentRepository extends JpaRepository<PaymentDO, PaymentId>
{
  /**
   * Busca los pagos por cliente
   * 
   * @param customer
   * @return
   */
  List<PaymentDO> findByCustomer( CustomerDO customer );

  /**
   * Busca los pagos por número de cliente
   * 
   * @param customer
   * @return
   */
  @Query("SELECT p FROM PaymentDO p WHERE p.customer.customerNumber = :customer")
  List<PaymentDO> findByCustomer( @Param("customer")
  Long customer );

}
