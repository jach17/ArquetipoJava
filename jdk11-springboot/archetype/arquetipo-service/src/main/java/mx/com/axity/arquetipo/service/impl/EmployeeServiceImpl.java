package mx.com.axity.arquetipo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import mx.com.axity.arquetipo.commons.response.graphql.EmployeeResponseDto;
import mx.com.axity.arquetipo.model.EmployeeDO;
import mx.com.axity.arquetipo.model.QEmployeeDO;
import mx.com.axity.arquetipo.persistence.graphql.EmployeeGraphQLRepository;
import mx.com.axity.arquetipo.service.EmployeeService;
import mx.com.axity.arquetipo.service.util.EmployeeResponseDtoTransformer;

/**
 * @author guillermo.segura@axity.com
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService
{
  @Autowired
  private EmployeeGraphQLRepository employeeGraphQLRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EmployeeResponseDto> getAllEmployees( String lastName, String firstName, String email )
  {
    var employee = QEmployeeDO.employeeDO;

    var predicates = new ArrayList<Predicate>();
    if( StringUtils.isNotBlank( lastName ) )
    {
      predicates.add( employee.lastName.containsIgnoreCase( lastName ) );
    }

    if( StringUtils.isNotBlank( firstName ) )
    {
      predicates.add( employee.firstName.containsIgnoreCase( firstName ) );
    }

    if( StringUtils.isNotBlank( email ) )
    {
      predicates.add( employee.email.containsIgnoreCase( email ) );
    }

    var employees = new ArrayList<EmployeeResponseDto>();

    if( predicates.isEmpty() )
    {
      var result = this.employeeGraphQLRepository.findAll();
      employees.addAll( result.stream().map( employeeDO -> EmployeeResponseDtoTransformer.transform( employeeDO ) )
          .collect( Collectors.toList() ) );
    }
    else
    {
      var result = this.employeeGraphQLRepository.findAll( ExpressionUtils.allOf( predicates ) );
      result.forEach( employeeDO -> employees.add( EmployeeResponseDtoTransformer.transform( employeeDO ) ) );
    }

    return employees;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EmployeeResponseDto getEmployeeById( @NotNull Long employeeNumber )
  {
    var employeeDO = this.employeeGraphQLRepository.findById( employeeNumber ).orElse( null );
    return EmployeeResponseDtoTransformer.transform( employeeDO );
  }

}
