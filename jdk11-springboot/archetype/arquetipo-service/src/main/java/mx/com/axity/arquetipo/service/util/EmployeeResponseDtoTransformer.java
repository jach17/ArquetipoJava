package mx.com.axity.arquetipo.service.util;

import java.util.ArrayList;

import mx.com.axity.arquetipo.commons.request.graphql.EmployeeQueryDto;
import mx.com.axity.arquetipo.commons.response.graphql.CustomerGraphlQLDto;
import mx.com.axity.arquetipo.commons.response.graphql.EmployeeGraphQLDto;
import mx.com.axity.arquetipo.model.EmployeeDO;

/**
 * @author guillermo.segura@axity.com
 */
public final class EmployeeResponseDtoTransformer
{
  private EmployeeResponseDtoTransformer()
  {
  }

  /**
   * Transforma una entidad {@link mx.com.axity.arquetipo.model.EmployeeDO} en un dto
   * {@link mx.com.axity.arquetipo.commons.response.graphql.EmployeeGraphQLDto}
   * 
   * @param entity
   * @param query
   * @return
   */
  public static EmployeeGraphQLDto transform( EmployeeDO entity, EmployeeQueryDto query )
  {
    return transform( entity, query, true );
  }

  private static EmployeeGraphQLDto transform( EmployeeDO entity, EmployeeQueryDto query, boolean checkSupervisor )
  {
    EmployeeGraphQLDto dto = null;
    if( entity != null )
    {
      dto = new EmployeeGraphQLDto();
      dto.setEmployeeNumber( entity.getEmployeeNumber() );
      dto.setLastName( entity.getLastName() );
      dto.setFirstName( entity.getFirstName() );
      dto.setExtension( entity.getExtension() );
      dto.setEmail( entity.getEmail() );
      dto.setJobTitle( entity.getJobTitle() );
      if( checkSupervisor && query.isReportsToProjection() )
      {
        var wrapperSupervisor = new EmployeeQueryDto();
        dto.setReportsTo( transform( entity.getReportsTo(), wrapperSupervisor, false ) );
      }

      if( query.isOfficeProjection() )
      {
        dto.setOffice( OfficeGraphQLDtoTransformer.transform( entity.getOffice() ) );
      }

      if( query.isCustomersProjection() )
      {
        var customers = new ArrayList<CustomerGraphlQLDto>();
        entity.getCustomers().forEach( x -> customers.add( CustomerGraphQLDtoTransformer.transform( x ) ) );
        dto.setCustomers( customers );
      }
    }
    return dto;
  }
}
