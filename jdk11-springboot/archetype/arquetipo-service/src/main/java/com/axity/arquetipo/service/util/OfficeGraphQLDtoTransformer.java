package com.axity.arquetipo.service.util;

import com.axity.arquetipo.commons.response.graphql.OfficeGraphQLDto;
import com.axity.arquetipo.model.OfficeDO;

/**
 * @author guillermo.segura@axity.com
 */
public final class OfficeGraphQLDtoTransformer
{
  private OfficeGraphQLDtoTransformer()
  {
  }

  /**
   * Transforma una entidad {@link com.axity.arquetipo.model.OfficeDO} en un dto
   * {@link com.axity.arquetipo.commons.response.graphql.OfficeGraphQLDto}
   * 
   * @param entity
   * @return
   */
  public static OfficeGraphQLDto transform( OfficeDO entity )
  {
    OfficeGraphQLDto office = null;

    if( entity != null )
    {
      office = new OfficeGraphQLDto();
      office.setId( entity.getId() );
      office.setCity( entity.getCity() );
      office.setPhone( entity.getPhone() );
      office.setAddressLine1( entity.getAddressLine1() );
      office.setAddressLine2( entity.getAddressLine2() );
      office.setState( entity.getState() );
      office.setCountry( entity.getCountry() );
      office.setPostalCode( entity.getPostalCode() );
      office.setTerritory( entity.getTerritory() );
    }
    return office;
  }
}
