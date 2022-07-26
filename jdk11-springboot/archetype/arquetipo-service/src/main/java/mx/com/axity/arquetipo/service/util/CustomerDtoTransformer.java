package mx.com.axity.arquetipo.service.util;

import org.springframework.beans.BeanUtils;

import mx.com.axity.arquetipo.commons.dto.CustomerDto;
import mx.com.axity.arquetipo.model.CustomerDO;

/**
 * @author guillermo.segura@axity.com
 */
public final class CustomerDtoTransformer
{
  private CustomerDtoTransformer()
  {
  }

  /**
   * Transforma una entidad {@link mx.com.axity.arquetipo.model.CustomerDO} en un dto
   * {@link mx.com.axity.arquetipo.commons.dto.CustomerDto}
   * 
   * @param x
   * @return
   */
  public static CustomerDto transform( CustomerDO entity )
  {
    CustomerDto dto = null;
    if( entity != null )
    {
      dto = new CustomerDto();

      BeanUtils.copyProperties( entity, dto, "salesRepEmployee", "payments" );
      dto.setSalesRepEmployee( EmployeeDtoTransformer.transform( entity.getSalesRepEmployee(), true ) );
    }

    return dto;
  }

}
