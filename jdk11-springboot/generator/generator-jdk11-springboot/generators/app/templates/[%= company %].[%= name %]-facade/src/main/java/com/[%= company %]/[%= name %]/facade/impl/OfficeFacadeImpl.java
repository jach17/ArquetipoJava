package com.[%= company %].[%= name %].facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.[%= company %].[%= name %].commons.dto.OfficeDto;
import com.[%= company %].[%= name %].commons.request.PaginatedRequestDto;
import com.[%= company %].[%= name %].commons.response.GenericResponseDto;
import com.[%= company %].[%= name %].commons.response.PaginatedResponseDto;
import com.[%= company %].[%= name %].facade.OfficeFacade;
import com.[%= company %].[%= name %].service.OfficeService;

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
