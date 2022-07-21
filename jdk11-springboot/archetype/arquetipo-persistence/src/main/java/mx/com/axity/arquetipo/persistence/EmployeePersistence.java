package mx.com.axity.arquetipo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.axity.arquetipo.model.EmployeeDO;
import mx.com.axity.arquetipo.model.OfficeDO;

/**
 * Interface de persistencia de {@link mx.com.axity.arquetipo.model.EmployeeDO}
 * 
 * @author guillermo.segura@axity.com
 */
@Repository
public interface EmployeePersistence extends JpaRepository<EmployeeDO, Long>
{
  /**
   * Busca los empleados por correo
   * 
   * @param email
   * @return
   */
  List<EmployeeDO> findByEmail( String email );

  /***
   * Busca los empleados por oficina
   * 
   * @param office
   * @return
   */
  List<EmployeeDO> findByOffice( OfficeDO office );

  /**
   * Busca los empleados por código de oficina
   * 
   * @param officeCode
   * @return
   */
  @Query("SELECT e FROM EmployeeDO e WHERE e.office.officeCode = :officeCode")
  List<EmployeeDO> findByOfficeCode( @Param("officeCode")
  String officeCode );

  /**
   * Busca los empleados por supervisor
   * 
   * @param reportsTo
   * @return
   */
  List<EmployeeDO> findByReportsTo( EmployeeDO reportsTo );

  /**
   * Busca los empleados por el número de empleado del supervisor
   * 
   * @param reportsTo
   * @return
   */
  @Query("SELECT e FROM EmployeeDO e WHERE e.reportsTo.employeeNumber = :reportsTo")
  List<EmployeeDO> findByReportsTo( @Param("reportsTo")
  Long reportsTo );

}
