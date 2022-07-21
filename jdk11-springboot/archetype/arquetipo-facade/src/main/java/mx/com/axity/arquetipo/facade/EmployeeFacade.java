package mx.com.axity.arquetipo.facade;

import java.util.List;

import javax.validation.constraints.NotNull;

import mx.com.axity.arquetipo.commons.response.graphql.EmployeeResponseDto;

/**
 * @author guillermo.segura@axity.com
 */

public interface EmployeeFacade
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
