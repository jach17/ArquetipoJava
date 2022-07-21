package mx.com.axity.arquetipo.service.util;

import mx.com.axity.arquetipo.commons.response.graphql.OfficeResponseDto;
import mx.com.axity.arquetipo.model.OfficeDO;

/**
 * @author guillermo.segura@axity.com
 */
public final class OfficeResponseDtoTransformer
{
  private OfficeResponseDtoTransformer()
  {
  }

  /**
   * Transforma una entidad {@link mx.com.axity.arquetipo.model.OfficeDO} en un dto
   * {@link mx.com.axity.arquetipo.commons.response.graphql.OfficeResponseDto}
   * 
   * @param entity
   * @return
   */
  public static OfficeResponseDto transform( OfficeDO entity )
  {
    OfficeResponseDto office = null;

    if( entity != null )
    {
      office = new OfficeResponseDto();
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
