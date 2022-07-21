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
 * @author guillermo.segura@axity.com
 *
 */
@Controller
public class EmployeeController
{

  @Autowired
  private EmployeeFacade employeeFacade;
  
  @QueryMapping(name="employees")
  public List<EmployeeResponseDto> getAllEmployees(
      @Argument String lastName,
      @Argument String firstName,
      @Argument String email){
    
    return this.employeeFacade.getAllEmployees(lastName, firstName, email);
  }
  
  @QueryMapping(name="employee")
  public EmployeeResponseDto getEmployeeById(@Argument @NotNull Long employeeNumber) {
    return this.employeeFacade.getEmployeeById(employeeNumber);
  }
  
  
}
