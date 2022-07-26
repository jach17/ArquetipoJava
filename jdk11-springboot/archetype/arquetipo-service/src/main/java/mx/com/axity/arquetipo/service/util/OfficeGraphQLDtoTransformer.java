package mx.com.axity.arquetipo.service.util;

import mx.com.axity.arquetipo.commons.response.graphql.OfficeGraphQLDto;
import mx.com.axity.arquetipo.model.OfficeDO;

/**
 * @author guillermo.segura@axity.com
 */
public final class OfficeGraphQLDtoTransformer
{
  private OfficeGraphQLDtoTransformer()
  {
  }

  /**
   * Transforma una entidad {@link mx.com.axity.arquetipo.model.OfficeDO} en un dto
   * {@link mx.com.axity.arquetipo.commons.response.graphql.OfficeGraphQLDto}
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
      office.setOfficeCode( entity.getOfficeCode() );
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
