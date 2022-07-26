package mx.com.axity.arquetipo.service.util;

import org.springframework.beans.BeanUtils;

import mx.com.axity.arquetipo.commons.response.graphql.CustomerGraphlQLDto;
import mx.com.axity.arquetipo.model.CustomerDO;

/**
 * @author guillermo.segura@axity.com
 */
public final class CustomerGraphQLDtoTransformer
{
  private CustomerGraphQLDtoTransformer()
  {
  }

  public static CustomerGraphlQLDto transform( CustomerDO entity )
  {
    CustomerGraphlQLDto dto = null;
    if( entity != null )
    {
      dto = new CustomerGraphlQLDto();
      BeanUtils.copyProperties( entity, dto, "salesRepEmployee", "payments" );
      dto.setSalesRepEmployee( entity.getSalesRepEmployee().getEmployeeNumber() );
    }
    return dto;
  }
}
