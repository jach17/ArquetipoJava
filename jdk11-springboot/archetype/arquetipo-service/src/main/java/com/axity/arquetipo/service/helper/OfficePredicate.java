package com.axity.arquetipo.service.helper;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axity.arquetipo.model.QOfficeDO;
import com.querydsl.core.types.Predicate;

/**
 * @author guillermo.segura@axity.com
 */
public final class OfficePredicate
{
  private OfficePredicate()
  {
  }

  /**
   * @param officeCode
   * @param office
   * @param predicates
   */
  public static void evaluateOfficeId( Integer id, QOfficeDO office, List<Predicate> predicates )
  {
    if( id != null )
    {
      predicates.add( office.id.eq( id ) );
    }
  }

  /**
   * @param city
   * @param office
   * @param predicates
   */
  public static void evaluateOfficeCity( String city, QOfficeDO office, List<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( city ) )
    {
      predicates.add( office.city.containsIgnoreCase( city ) );
    }
  }

  /**
   * @param phone
   * @param office
   * @param predicates
   */
  public static void evaluateOfficePhone( String phone, QOfficeDO office, List<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( phone ) )
    {
      predicates.add( office.phone.containsIgnoreCase( phone ) );
    }
  }

  /**
   * @param addressLine1
   * @param office
   * @param predicates
   */
  public static void evaluateOfficeAddressLine1( String addressLine1, QOfficeDO office,
      List<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( addressLine1 ) )
    {
      predicates.add( office.addressLine1.containsIgnoreCase( addressLine1 ) );
    }
  }

  /**
   * @param addressLine2
   * @param office
   * @param predicates
   */
  public static void evaluateOfficeAddressLine2( String addressLine2, QOfficeDO office,
      List<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( addressLine2 ) )
    {
      predicates.add( office.addressLine2.containsIgnoreCase( addressLine2 ) );
    }
  }

  /**
   * @param state
   * @param office
   * @param predicates
   */
  public static void evaluateOfficeState( String state, QOfficeDO office, List<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( state ) )
    {
      predicates.add( office.state.containsIgnoreCase( state ) );
    }
  }

  /**
   * @param country
   * @param office
   * @param predicates
   */
  public static void evaluateOfficeCountry( String country, QOfficeDO office, List<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( country ) )
    {
      predicates.add( office.country.containsIgnoreCase( country ) );
    }
  }

  /**
   * @param territory
   * @param office
   * @param predicates
   */
  public static void evaluateOfficeTerritory( String territory, QOfficeDO office, List<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( territory ) )
    {
      predicates.add( office.territory.containsIgnoreCase( territory ) );
    }
  }

  /**
   * @param postalCode
   * @param office
   * @param predicates
   */
  public static void evaluateOfficePostalCode( String postalCode, QOfficeDO office, List<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( postalCode ) )
    {
      predicates.add( office.postalCode.containsIgnoreCase( postalCode ) );
    }
  }

}