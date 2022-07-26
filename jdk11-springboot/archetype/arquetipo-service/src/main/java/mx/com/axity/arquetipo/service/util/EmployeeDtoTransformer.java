package mx.com.axity.arquetipo.service.util;

import org.springframework.beans.BeanUtils;

import mx.com.axity.arquetipo.commons.dto.EmployeeDto;
import mx.com.axity.arquetipo.model.EmployeeDO;

/**
 * @author guillermo.segura@axity.com
 */
public final class EmployeeDtoTransformer
{
  private EmployeeDtoTransformer()
  {
  }

  /**
   * @param salesRepEmployee
   * @param b
   * @return
   */
  public static EmployeeDto transform( EmployeeDO entity, boolean checkSupervisor )
  {
    EmployeeDto dto = null;
    if( entity != null )
    {
      dto = new EmployeeDto();
      BeanUtils.copyProperties( entity, dto, "office", "reportsTo", "employees", "customers" );
      if( entity.getReportsTo() != null )
      {
        dto.setReportsTo( entity.getReportsTo().getEmployeeNumber() );
      }
      
      if( entity.getOffice() != null )
      {
        dto.setOfficeCode( entity.getOffice().getOfficeCode() );
      }

    }
    return dto;
  }

}
