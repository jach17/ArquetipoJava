package com.axity.arquetipo.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axity.arquetipo.commons.dto.OfficeDto;
import com.axity.arquetipo.commons.request.PaginatedRequestDto;
import com.axity.arquetipo.commons.request.graphql.OfficeQueryDto;
import com.axity.arquetipo.commons.response.GenericResponseDto;
import com.axity.arquetipo.commons.response.PaginatedResponseDto;
import com.axity.arquetipo.commons.response.graphql.OfficeGraphQLDto;
import com.axity.arquetipo.facade.OfficeFacade;
import com.axity.arquetipo.service.OfficeService;

import graphql.schema.DataFetchingEnvironment;

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
  public GenericResponseDto<OfficeDto> find( Integer id )
  {
    return this.officeService.find( id );
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
  public GenericResponseDto<Boolean> delete( Integer officeid )
  {
    return this.officeService.delete( officeid );
  }

  /**
   * {@inheritDoc}
   */
  public List<OfficeGraphQLDto> findGraphQL( OfficeQueryDto query, DataFetchingEnvironment env )
  {
    return this.officeService.findGraphQL( query, env );
  }

  /**
   * {@inheritDoc}
   */
  public void processMessage( String message )
  {
    this.officeService.processMessage( message );
  }
}
