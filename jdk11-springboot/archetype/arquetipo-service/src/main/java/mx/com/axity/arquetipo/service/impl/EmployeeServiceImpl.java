package mx.com.axity.arquetipo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import mx.com.axity.arquetipo.commons.request.graphql.EmployeeQueryDto;
import mx.com.axity.arquetipo.commons.response.graphql.EmployeeGraphQLDto;
import mx.com.axity.arquetipo.commons.response.graphql.OfficeGraphQLDto;
import mx.com.axity.arquetipo.model.QEmployeeDO;
import mx.com.axity.arquetipo.persistence.graphql.EmployeeGraphQLRepository;
import mx.com.axity.arquetipo.service.EmployeeService;
import mx.com.axity.arquetipo.service.helper.EmployeePredicate;
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
   * @param predicates
   * @param query
   * @return
   */
  private List<EmployeeGraphQLDto> processPredicate( List<Predicate> predicates, EmployeeQueryDto query )
  {
    var employees = new ArrayList<EmployeeGraphQLDto>();

    if( predicates.isEmpty() )
    {
      var result = this.employeeGraphQLRepository.findAll();
      employees
          .addAll( result.stream().map( employeeDO -> EmployeeResponseDtoTransformer.transform( employeeDO, query ) )
              .collect( Collectors.toList() ) );
    }
    else
    {
      var result = this.employeeGraphQLRepository.findAll( ExpressionUtils.allOf( predicates ) );
      result.forEach( employeeDO -> employees.add( EmployeeResponseDtoTransformer.transform( employeeDO, query ) ) );
    }

    return employees;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EmployeeGraphQLDto> getByExample( EmployeeQueryDto query )
  {
    var employee = QEmployeeDO.employeeDO;
    var predicates = this.getEmployeePredicates( employee, query, true );

    return this.processPredicate( predicates, query );
  }

  /**
   * @param employee
   * @param reportsTo
   * @return
   */
  private List<Predicate> getEmployeePredicates( QEmployeeDO employee, EmployeeQueryDto wrapper,
      boolean checkSupervisor )
  {
    var predicates = new ArrayList<Predicate>();
    if( Stream.of( wrapper, wrapper.getQuery() ).allMatch( Objects::nonNull ) )
    {
      EmployeePredicate.evaluateLastName( wrapper.getQuery().getLastName(), employee, predicates );
      EmployeePredicate.evaluateFirstName( wrapper.getQuery().getFirstName(), employee, predicates );
      EmployeePredicate.evaluateEmail( wrapper.getQuery().getEmail(), employee, predicates );
      EmployeePredicate.evaluateJobTitle( wrapper.getQuery().getJobTitle(), employee, predicates );
      EmployeePredicate.evaluateExtension( wrapper.getQuery().getExtension(), employee, predicates );
      EmployeePredicate.evaluateEmployeeNumber( wrapper.getQuery().getEmployeeNumber(), employee, predicates );

      predicates.addAll( this.getOfficePredicates( employee, wrapper.getQuery().getOffice() ) );

      var supervisorWrapper = new EmployeeQueryDto( wrapper.getQuery().getReportsTo() );
      evaluateSupervisor( employee, supervisorWrapper, checkSupervisor, predicates );
    }

    return predicates;
  }

  /**
   * @param employee
   * @param query
   * @param checkSupervisor
   * @param predicates
   */
  private void evaluateSupervisor( QEmployeeDO employee, EmployeeQueryDto wrapper, boolean checkSupervisor,
      ArrayList<Predicate> predicates )
  {
    if( checkSupervisor )
    {
      predicates.addAll( this.getEmployeePredicates( employee, wrapper, false ) );
    }
  }

  /**
   * @param employee
   * @param office
   * @return
   */
  private List<Predicate> getOfficePredicates( QEmployeeDO employee, OfficeGraphQLDto office )
  {
    var predicates = new ArrayList<Predicate>();
    if( office != null )
    {
      EmployeePredicate.evaluateOfficeOfficeCode( office.getOfficeCode(), employee, predicates );
      EmployeePredicate.evaluateOfficeCity( office.getCity(), employee, predicates );
      EmployeePredicate.evaluateOfficePhone( office.getPhone(), employee, predicates );
      EmployeePredicate.evaluateOfficeAddressLine1( office.getAddressLine1(), employee, predicates );
      EmployeePredicate.evaluateOfficeAddressLine2( office.getAddressLine2(), employee, predicates );
      EmployeePredicate.evaluateOfficeState( office.getState(), employee, predicates );
      EmployeePredicate.evaluateOfficeCountry( office.getCountry(), employee, predicates );
      EmployeePredicate.evaluateOfficePostalCode( office.getPostalCode(), employee, predicates );
      EmployeePredicate.evaluateOfficeTerritory( office.getTerritory(), employee, predicates );
    }
    return predicates;
  }

}
