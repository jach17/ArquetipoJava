package mx.com.axity.arquetipo.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.axity.arquetipo.commons.dto.OfficeDto;
import mx.com.axity.arquetipo.commons.request.PaginatedRequestDto;
import mx.com.axity.arquetipo.commons.response.GenericResponseDto;
import mx.com.axity.arquetipo.commons.response.PaginatedResponseDto;
import mx.com.axity.arquetipo.facade.OfficeFacade;
import mx.com.axity.arquetipo.service.OfficeService;

/**
 * @author guillermo.segura@axity.com
 */
@Service
@Transactional
public class OfficeFacadeImpl implements OfficeFacade
{
  @Autowired
  private OfficeService officeService;
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<OfficeDto> findOffices( PaginatedRequestDto request )
  {
    return this.officeService.findOffices( request );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<OfficeDto> find( String officeCode )
  {
    return this.officeService.find( officeCode );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<OfficeDto> create( OfficeDto office )
  {
    return this.officeService.create( office );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( OfficeDto office )
  {
    return this.officeService.update( office );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( String officeCode )
  {
    return this.officeService.delete( officeCode );
  }

}
