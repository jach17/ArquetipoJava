package mx.com.axity.arquetipo.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import graphql.schema.DataFetchingEnvironment;
import mx.com.axity.arquetipo.commons.response.graphql.EmployeeGraphQLDto;
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
  public List<EmployeeGraphQLDto> getAllEmployees( @Argument String lastName, @Argument String firstName,
      @Argument String email, DataFetchingEnvironment env )
  {
    return this.employeeFacade.getAllEmployees( lastName, firstName, email, env );
  }

  /**
   * Método para consultar los empleados por el número de empleado
   * 
   * @param employeeNumber
   * @return
   */
  @QueryMapping(name = "employee")
  public EmployeeGraphQLDto getEmployeeById( @Argument
  @NotNull Long employeeNumber, DataFetchingEnvironment env )
  {
    return this.employeeFacade.getEmployeeById( employeeNumber, env );
  }

  /**
   * Método para consultar los empleados por ejemplo
   * 
   * @param query
   * @return
   */
  @QueryMapping(name = "employeesByExample")
  public List<EmployeeGraphQLDto> getAllEmployeesByExample( @Argument EmployeeGraphQLDto query,
      DataFetchingEnvironment env )
  {
    return this.employeeFacade.getByExample( query, env );
  }

}
