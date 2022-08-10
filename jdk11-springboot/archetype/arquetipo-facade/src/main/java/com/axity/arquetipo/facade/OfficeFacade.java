package com.axity.arquetipo.facade;

import java.util.List;

import com.axity.arquetipo.commons.dto.OfficeDto;
import com.axity.arquetipo.commons.request.PaginatedRequestDto;
import com.axity.arquetipo.commons.request.graphql.OfficeQueryDto;
import com.axity.arquetipo.commons.response.GenericResponseDto;
import com.axity.arquetipo.commons.response.PaginatedResponseDto;
import com.axity.arquetipo.commons.response.graphql.OfficeGraphQLDto;

import graphql.schema.DataFetchingEnvironment;

/**
 * @author guillermo.segura@axity.com
 */
public interface OfficeFacade
{
  /**
   * Método para buscar las oficinas
   * 
   * @param request
   * @return
   */
  PaginatedResponseDto<OfficeDto> findOffices( PaginatedRequestDto request );

  /**
   * Busca una oficina por código
   * 
   * @param officeId
   * @return
   */
  GenericResponseDto<OfficeDto> find( Integer officeId );

  /**
   * Crea una oficina
   * 
   * @param office
   * @return
   */
  GenericResponseDto<OfficeDto> create( OfficeDto office );

  /**
   * Actualiza una oficina
   * 
   * @param office
   * @return
   */
  GenericResponseDto<Boolean> update( OfficeDto office );

  /**
   * Elimina una oficina
   * 
   * @param officeId
   * @return
   */
  GenericResponseDto<Boolean> delete( Integer officeId );

  /**
   * @param query
   * @param env
   * @return
   */
  List<OfficeGraphQLDto> findGraphQL( OfficeQueryDto query, DataFetchingEnvironment env );

  /**
   * Procesa un mensaje
   * 
   * @param message
   */
  void processMessage( String message );
}
