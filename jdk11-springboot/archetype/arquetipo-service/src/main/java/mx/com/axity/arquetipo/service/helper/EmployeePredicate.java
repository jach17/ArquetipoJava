package mx.com.axity.arquetipo.service.helper;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.querydsl.core.types.Predicate;

import mx.com.axity.arquetipo.model.QEmployeeDO;

/**
 * @author guillermo.segura@axity.com
 */
public final class EmployeePredicate
{
  private EmployeePredicate()
  {
  }

  /**
   * @param employee
   * @param query
   * @param predicates
   */
  public static void evaluateEmployeeNumber ( Long employeeNumber, QEmployeeDO employee, 
      ArrayList<Predicate> predicates )
  {
    if( employeeNumber != null )
    {
      predicates.add( employee.employeeNumber.eq( employeeNumber ) );
    }
  }
  
  /**
   * @param lastName
   * @param employee
   * @param predicates
   */
  public static void evaluateLastName( String lastName, QEmployeeDO employee, ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( lastName ) )
    {
      predicates.add( employee.lastName.containsIgnoreCase( lastName ) );
    }
  }

  /**
   * @param firstName
   * @param employee
   * @param predicates
   */
  public static void evaluateFirstName( String firstName, QEmployeeDO employee, ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( firstName ) )
    {
      predicates.add( employee.firstName.containsIgnoreCase( firstName ) );
    }
  }

  /**
   * @param email
   * @param employee
   * @param predicates
   */
  public static void evaluateEmail( String email, QEmployeeDO employee, ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( email ) )
    {
      predicates.add( employee.email.containsIgnoreCase( email ) );
    }
  }

  /**
   * @param jobTitle
   * @param employee
   * @param predicates
   */
  public static void evaluateJobTitle( String jobTitle, QEmployeeDO employee, ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( jobTitle ) )
    {
      predicates.add( employee.jobTitle.containsIgnoreCase( jobTitle ) );
    }
  }

  /**
   * @param extension
   * @param employee
   * @param predicates
   */
  public static void evaluateExtension( String extension, QEmployeeDO employee, ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( extension ) )
    {
      predicates.add( employee.extension.containsIgnoreCase( extension ) );
    }
  }

  /**
   * @param employee
   * @param office
   * @param predicates
   */
  public static void evaluateOfficeOfficeCode( String officeCode, QEmployeeDO employee,
      ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( officeCode ) )
    {
      predicates.add( employee.office.officeCode.eq( officeCode ) );
    }
  }

  /**
   * @param employee
   * @param office
   * @param predicates
   */
  public static void evaluateOfficeCity( String city, QEmployeeDO employee, ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( city ) )
    {
      predicates.add( employee.office.city.containsIgnoreCase( city ) );
    }
  }

  /**
   * @param employee
   * @param office
   * @param predicates
   */
  public static void evaluateOfficePhone( String phone, QEmployeeDO employee, ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( phone ) )
    {
      predicates.add( employee.office.phone.containsIgnoreCase( phone ) );
    }
  }

  /**
   * @param employee
   * @param office
   * @param predicates
   */
  public static void evaluateOfficeAddressLine1( String addressLine1, QEmployeeDO employee,
      ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( addressLine1 ) )
    {
      predicates.add( employee.office.addressLine1.containsIgnoreCase( addressLine1 ) );
    }
  }

  /**
   * @param employee
   * @param office
   * @param predicates
   */
  public static void evaluateOfficeAddressLine2( String addressLine2, QEmployeeDO employee,
      ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( addressLine2 ) )
    {
      predicates.add( employee.office.addressLine2.containsIgnoreCase( addressLine2 ) );
    }
  }

  /**
   * @param employee
   * @param office
   * @param predicates
   */
  public static void evaluateOfficeState( String state, QEmployeeDO employee, ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( state ) )
    {
      predicates.add( employee.office.state.containsIgnoreCase( state ) );
    }
  }

  /**
   * @param employee
   * @param office
   * @param predicates
   */
  public static void evaluateOfficeCountry( String country, QEmployeeDO employee, ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( country ) )
    {
      predicates.add( employee.office.country.containsIgnoreCase( country ) );
    }
  }

  /**
   * @param employee
   * @param office
   * @param predicates
   */
  public static void evaluateOfficeTerritory( String territory, QEmployeeDO employee, ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( territory ) )
    {
      predicates.add( employee.office.territory.containsIgnoreCase( territory ) );
    }
  }

  /**
   * @param employee
   * @param office
   * @param predicates
   */
  public static void evaluateOfficePostalCode( String postalCode, QEmployeeDO employee,
      ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( postalCode ) )
    {
      predicates.add( employee.office.postalCode.containsIgnoreCase( postalCode ) );
    }
  }

}