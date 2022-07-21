package mx.com.axity.arquetipo.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import mx.com.axity.arquetipo.commons.response.graphql.EmployeeResponseDto;

/**
 * Interface de servicios para empleados
 * 
 * @author guillermo.segura@axity.com
 */
public interface EmployeeService
{

  /**
   * Busca los empleados por apellido, nombre y/o correo
   * 
   * @param lastName
   * @param firstName
   * @param email
   * @return
   */
  List<EmployeeResponseDto> getAllEmployees( String lastName, String firstName, String email );

  /**
   * Busca los empleados por número de empleados
   * 
   * @param employeeNumber
   * @return
   */
  EmployeeResponseDto getEmployeeById( @NotNull Long employeeNumber );

  /**
   * Busca los empleados por ejemplo
   * 
   * @param query
   * @return
   */
  List<EmployeeResponseDto> getByExample( EmployeeResponseDto query );

}
