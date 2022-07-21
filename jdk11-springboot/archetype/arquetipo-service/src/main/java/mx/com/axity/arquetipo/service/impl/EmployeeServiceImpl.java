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
import mx.com.axity.arquetipo.commons.response.graphql.OfficeResponseDto;
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

    return processPredicate( predicates );
  }

  /**
   * @param predicates
   * @return
   */
  private List<EmployeeResponseDto> processPredicate( List<Predicate> predicates )
  {
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

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EmployeeResponseDto> getByExample( EmployeeResponseDto query )
  {
    var employee = QEmployeeDO.employeeDO;
    var predicates = this.getEmployeePredicates( employee, query, true );

    return this.processPredicate( predicates );
  }

  /**
   * @param employee
   * @param reportsTo
   * @return
   */
  private List<Predicate> getEmployeePredicates( QEmployeeDO employee, EmployeeResponseDto query,
      boolean checkSupervisor )
  {
    var predicates = new ArrayList<Predicate>();
    if( query != null )
    {
      if( StringUtils.isNotBlank( query.getLastName() ) )
      {
        predicates.add( employee.lastName.containsIgnoreCase( query.getLastName() ) );
      }

      if( StringUtils.isNotBlank( query.getFirstName() ) )
      {
        predicates.add( employee.firstName.containsIgnoreCase( query.getFirstName() ) );
      }

      if( StringUtils.isNotBlank( query.getEmail() ) )
      {
        predicates.add( employee.email.containsIgnoreCase( query.getEmail() ) );
      }

      if( StringUtils.isNotBlank( query.getEmail() ) )
      {
        predicates.add( employee.email.containsIgnoreCase( query.getEmail() ) );
      }

      if( StringUtils.isNotBlank( query.getJobTitle() ) )
      {
        predicates.add( employee.jobTitle.containsIgnoreCase( query.getJobTitle() ) );
      }

      if( query.getEmployeeNumber() != null )
      {
        predicates.add( employee.employeeNumber.eq( query.getEmployeeNumber() ) );
      }

      predicates.addAll( this.getOfficePredicates( employee, query.getOffice() ) );

      if( checkSupervisor )
      {
        predicates.addAll( this.getEmployeePredicates( employee, query.getReportsTo(), false ) );
      }
    }

    return predicates;
  }

  /**
   * @param employee
   * @param office
   * @return
   */
  private List<Predicate> getOfficePredicates( QEmployeeDO employee, OfficeResponseDto office )
  {
    var predicates = new ArrayList<Predicate>();
    if( office != null )
    {
      if( StringUtils.isNotBlank( office.getOfficeCode() ) )
      {
        predicates.add( employee.office.officeCode.eq( office.getOfficeCode() ) );
      }
      if( StringUtils.isNotBlank( office.getCity() ) )
      {
        predicates.add( employee.office.city.containsIgnoreCase( office.getCity() ) );
      }
      if( StringUtils.isNotBlank( office.getPhone() ) )
      {
        predicates.add( employee.office.phone.containsIgnoreCase( office.getPhone() ) );
      }
      if( StringUtils.isNotBlank( office.getAddressLine1() ) )
      {
        predicates.add( employee.office.addressLine1.containsIgnoreCase( office.getAddressLine1() ) );
      }
      if( StringUtils.isNotBlank( office.getAddressLine2() ) )
      {
        predicates.add( employee.office.addressLine2.containsIgnoreCase( office.getAddressLine2() ) );
      }
      if( StringUtils.isNotBlank( office.getState() ) )
      {
        predicates.add( employee.office.state.containsIgnoreCase( office.getState() ) );
      }
      if( StringUtils.isNotBlank( office.getCountry() ) )
      {
        predicates.add( employee.office.country.containsIgnoreCase( office.getCountry() ) );
      }
      if( StringUtils.isNotBlank( office.getPostalCode() ) )
      {
        predicates.add( employee.office.postalCode.containsIgnoreCase( office.getPostalCode() ) );
      }
      if( StringUtils.isNotBlank( office.getTerritory() ) )
      {
        predicates.add( employee.office.territory.containsIgnoreCase( office.getTerritory() ) );
      }
    }
    return predicates;
  }

}
