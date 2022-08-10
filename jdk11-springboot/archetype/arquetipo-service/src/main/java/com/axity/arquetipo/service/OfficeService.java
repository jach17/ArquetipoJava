package com.axity.arquetipo.service;

import java.util.List;

import com.axity.arquetipo.commons.dto.OfficeDto;
import com.axity.arquetipo.commons.request.PaginatedRequestDto;
import com.axity.arquetipo.commons.request.graphql.OfficeQueryDto;
import com.axity.arquetipo.commons.response.GenericResponseDto;
import com.axity.arquetipo.commons.response.PaginatedResponseDto;
import com.axity.arquetipo.commons.response.graphql.OfficeGraphQLDto;

import graphql.schema.DataFetchingEnvironment;

/**
 * Interface de servicios para oficinas
 * 
 * @author guillermo.segura@axity.com
 */
public interface OfficeService
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
   * @param id
   * @return
   */
  GenericResponseDto<OfficeDto> find( Integer id );

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
   * @param officeCode
   * @return
   */
  GenericResponseDto<Boolean> delete( Integer id );

  /**
   * Consulat por graphql
   * 
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
