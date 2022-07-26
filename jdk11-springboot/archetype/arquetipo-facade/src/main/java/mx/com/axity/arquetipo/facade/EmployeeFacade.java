package mx.com.axity.arquetipo.facade;

import java.util.List;

import javax.validation.constraints.NotNull;

import graphql.schema.DataFetchingEnvironment;
import mx.com.axity.arquetipo.commons.response.graphql.EmployeeGraphQLDto;

/**
 * Interface fachada de empleados
 * 
 * @author guillermo.segura@axity.com
 */
public interface EmployeeFacade
{

  /**
   * Busca los empleados por apellido, nombre y/o correo
   * 
   * @param lastName
   * @param firstName
   * @param email
   * @param env
   * @return
   */
  List<EmployeeGraphQLDto> getAllEmployees( String lastName, String firstName, String email, DataFetchingEnvironment env );

  /**
   * Busca los empleados por número de empleados
   * 
   * @param employeeNumber
   * @param env
   * @return
   */
  EmployeeGraphQLDto getEmployeeById( @NotNull Long employeeNumber, DataFetchingEnvironment env );

  /**
   * Busca los empleados por ejemplo
   * 
   * @param query
   * @param env
   * @return
   */
  List<EmployeeGraphQLDto> getByExample( EmployeeGraphQLDto query, DataFetchingEnvironment env );

}
