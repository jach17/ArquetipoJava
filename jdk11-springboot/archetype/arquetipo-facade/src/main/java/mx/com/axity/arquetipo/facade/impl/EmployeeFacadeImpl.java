package mx.com.axity.arquetipo.facade.impl;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.axity.arquetipo.commons.response.graphql.EmployeeResponseDto;
import mx.com.axity.arquetipo.facade.EmployeeFacade;
import mx.com.axity.arquetipo.service.EmployeeService;

/**
 * @author guillermo.segura@axity.com
 */
@Service
@Transactional
public class EmployeeFacadeImpl implements EmployeeFacade
{
  @Autowired
  private EmployeeService employeeService;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EmployeeResponseDto> getAllEmployees( String lastName, String firstName, String email )
  {
    return this.employeeService.getAllEmployees( lastName, firstName, email );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EmployeeResponseDto getEmployeeById( @NotNull Long employeeNumber )
  {
    return this.employeeService.getEmployeeById( employeeNumber );
  }

}
