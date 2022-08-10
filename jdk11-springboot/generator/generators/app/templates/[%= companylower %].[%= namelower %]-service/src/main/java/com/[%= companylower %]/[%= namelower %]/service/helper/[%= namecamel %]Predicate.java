package com.[%= companylower %].[%= namelower %].service.helper;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.[%= companylower %].[%= namelower %].model.Q[%= namecamel %]DO;
import com.querydsl.core.types.Predicate;

/**
 * Class [%= namecamel %]Predicate
 * 
 * @author [%= username %]
 */
public final class [%= namecamel %]Predicate
{
  private [%= namecamel %]Predicate()
  {
  }

  /**
   * @param [%= namelower %]Code
   * @param [%= namelower %]
   * @param predicates
   */
  public static void evaluate[%= namecamel %]Id( Integer [%= namelower %]Id, Q[%= namecamel %]DO [%= namelower %], ArrayList<Predicate> predicates )
  {
    if( [%= namelower %]Id != null)
    {
      predicates.add( [%= namelower %].id.eq( [%= namelower %]Id ) );
    }
  }

  /**
   * @param city
   * @param [%= namelower %]
   * @param predicates
   */
  public static void evaluate[%= namecamel %]City( String city, Q[%= namecamel %]DO [%= namelower %], ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( city ) )
    {
      predicates.add( [%= namelower %].city.containsIgnoreCase( city ) );
    }
  }

  /**
   * @param phone
   * @param [%= namelower %]
   * @param predicates
   */
  public static void evaluate[%= namecamel %]Phone( String phone, Q[%= namecamel %]DO [%= namelower %], ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( phone ) )
    {
      predicates.add( [%= namelower %].phone.containsIgnoreCase( phone ) );
    }
  }

  /**
   * @param addressLine1
   * @param [%= namelower %]
   * @param predicates
   */
  public static void evaluate[%= namecamel %]AddressLine1( String addressLine1, Q[%= namecamel %]DO [%= namelower %],
      ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( addressLine1 ) )
    {
      predicates.add( [%= namelower %].addressLine1.containsIgnoreCase( addressLine1 ) );
    }
  }

  /**
   * @param addressLine2
   * @param [%= namelower %]
   * @param predicates
   */
  public static void evaluate[%= namecamel %]AddressLine2( String addressLine2, Q[%= namecamel %]DO [%= namelower %],
      ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( addressLine2 ) )
    {
      predicates.add( [%= namelower %].addressLine2.containsIgnoreCase( addressLine2 ) );
    }
  }

  /**
   * @param state
   * @param [%= namelower %]
   * @param predicates
   */
  public static void evaluate[%= namecamel %]State( String state, Q[%= namecamel %]DO [%= namelower %], ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( state ) )
    {
      predicates.add( [%= namelower %].state.containsIgnoreCase( state ) );
    }
  }

  /**
   * @param country
   * @param [%= namelower %]
   * @param predicates
   */
  public static void evaluate[%= namecamel %]Country( String country, Q[%= namecamel %]DO [%= namelower %], ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( country ) )
    {
      predicates.add( [%= namelower %].country.containsIgnoreCase( country ) );
    }
  }

  /**
   * @param territory
   * @param [%= namelower %]
   * @param predicates
   */
  public static void evaluate[%= namecamel %]Territory( String territory, Q[%= namecamel %]DO [%= namelower %], ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( territory ) )
    {
      predicates.add( [%= namelower %].territory.containsIgnoreCase( territory ) );
    }
  }

  /**
   * @param postalCode
   * @param [%= namelower %]
   * @param predicates
   */
  public static void evaluate[%= namecamel %]PostalCode( String postalCode, Q[%= namecamel %]DO [%= namelower %], ArrayList<Predicate> predicates )
  {
    if( StringUtils.isNotBlank( postalCode ) )
    {
      predicates.add( [%= namelower %].postalCode.containsIgnoreCase( postalCode ) );
    }
  }

}