package mx.com.axity.arquetipo.service;

import java.util.List;

import mx.com.axity.arquetipo.commons.request.graphql.EmployeeQueryDto;
import mx.com.axity.arquetipo.commons.response.graphql.EmployeeGraphQLDto;

/**
 * Interface de servicios para empleados
 * 
 * @author guillermo.segura@axity.com
 */
public interface EmployeeService
{
  /**
   * Busca los empleados por ejemplo
   * 
   * @param query
   * @return
   */
  List<EmployeeGraphQLDto> getByExample( EmployeeQueryDto query );

}
