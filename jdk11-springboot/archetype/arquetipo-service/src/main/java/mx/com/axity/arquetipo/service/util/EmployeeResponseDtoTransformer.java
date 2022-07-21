package mx.com.axity.arquetipo.service.util;

import mx.com.axity.arquetipo.commons.response.graphql.EmployeeResponseDto;
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
   * {@link mx.com.axity.arquetipo.commons.response.graphql.EmployeeResponseDto}
   * 
   * @param entity
   * @return
   */
  public static EmployeeResponseDto transform( EmployeeDO entity )
  {
    return transform( entity, true );
  }

  private static EmployeeResponseDto transform( EmployeeDO entity, boolean checkSupervisor )
  {
    EmployeeResponseDto dto = null;
    if( entity != null )
    {
      dto = new EmployeeResponseDto();
      dto.setEmployeeNumber( entity.getEmployeeNumber() );
      dto.setLastName( entity.getLastName() );
      dto.setFirstName( entity.getFirstName() );
      dto.setExtension( entity.getExtension() );
      dto.setEmail( entity.getEmail() );
      dto.setJobTitle( entity.getJobTitle() );
      if( checkSupervisor )
      {
        dto.setReportsTo( transform( entity.getReportsTo(), false ) );
      }
      dto.setOffice( OfficeResponseDtoTransformer.transform( entity.getOffice() ) );
    }
    return dto;
  }
}
