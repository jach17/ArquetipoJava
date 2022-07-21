package mx.com.axity.arquetipo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.axity.arquetipo.model.CustomerDO;
import mx.com.axity.arquetipo.model.EmployeeDO;

/**
 * Interface de persistencia de {@link mx.com.axity.arquetipo.model.CustomerDO}
 * 
 * @author guillermo.segura@axity.com
 */
@Repository
public interface CustomerPersistence extends JpaRepository<CustomerDO, Long>
{
  /**
   * Busca los clientes por país
   * 
   * @param country
   * @return
   */
  List<CustomerDO> findByCountry( String country );

  /**
   * Busca los clientes por ciudad
   * 
   * @param city
   * @return
   */
  List<CustomerDO> findByCity( String city );

  /**
   * Busca los clientes por nombre
   * 
   * @param customerName
   * @return
   */
  List<CustomerDO> findByCustomerName( String customerName );

  /**
   * Busca los clientes por nombre similar
   * 
   * @param customerName
   * @return
   */
//  @Query("SELECT c FROM CustomerDO c WHERE UPPER(c.customerName) like UPPER(%:customerName%)")
  List<CustomerDO> findByCustomerNameLikeIgnoreCase( @Param("customerName")
  String customerName );

  /**
   * Busca los clientes asignados a un representante de ventas (empleado)
   * 
   * @param salesRepEmployee
   * @return
   */
  List<CustomerDO> findBySalesRepEmployee( EmployeeDO salesRepEmployee );

  @Query("SELECT c FROM CustomerDO c WHERE c.salesRepEmployee.employeeNumber = :salesRepEmployee")
  List<CustomerDO> findBySalesRepEmployee( @Param("salesRepEmployee")
  Long salesRepEmployee );
}
