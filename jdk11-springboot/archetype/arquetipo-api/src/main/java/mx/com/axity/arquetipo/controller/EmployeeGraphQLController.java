package mx.com.axity.arquetipo.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import mx.com.axity.arquetipo.commons.response.graphql.EmployeeResponseDto;
import mx.com.axity.arquetipo.facade.EmployeeFacade;

/**
 * Controlador de empleados (GraphQL)
 * 
 * @author guillermo.segura@axity.com
 */
@Controller
public class EmployeeGraphQLController
{

  @Autowired
  private EmployeeFacade employeeFacade;

  /**
   * Método para consultar los empleados por la proyección por argumentos simples
   * 
   * @param lastName
   * @param firstName
   * @param email
   * @return
   */
  @QueryMapping(name = "employees")
  public List<EmployeeResponseDto> getAllEmployees( @Argument String lastName, @Argument String firstName,
      @Argument String email )
  {

    return this.employeeFacade.getAllEmployees( lastName, firstName, email );
  }

  /**
   * Método para consultar los empleados por el número de empleado
   * 
   * @param employeeNumber
   * @return
   */
  @QueryMapping(name = "employee")
  public EmployeeResponseDto getEmployeeById( @Argument
  @NotNull Long employeeNumber )
  {
    return this.employeeFacade.getEmployeeById( employeeNumber );
  }

  /**
   * Método para consultar los empleados por ejemplo
   * 
   * @param query
   * @return
   */
  @QueryMapping(name = "employeesByExample")
  public List<EmployeeResponseDto> getAllEmployeesByExample( @Argument EmployeeResponseDto query )
  {
    return this.employeeFacade.getByExample( query );
  }

}
