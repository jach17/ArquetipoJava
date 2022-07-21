package mx.com.axity.arquetipo.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import mx.com.axity.arquetipo.commons.response.graphql.EmployeeResponseDto;

/**
 * Interface de servicios para
 * 
 * @author guillermo.segura@axity.com
 */
public interface EmployeeService
{

  /**
   * @param lastName
   * @param firstName
   * @param email
   * @return
   */
  List<EmployeeResponseDto> getAllEmployees( String lastName, String firstName, String email );

  /**
   * @param employeeNumber
   * @return
   */
  EmployeeResponseDto getEmployeeById( @NotNull Long employeeNumber );

}
