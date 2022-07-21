package mx.com.axity.arquetipo.service;

import mx.com.axity.arquetipo.commons.dto.OfficeDto;
import mx.com.axity.arquetipo.commons.request.PaginatedRequestDto;
import mx.com.axity.arquetipo.commons.response.GenericResponseDto;
import mx.com.axity.arquetipo.commons.response.PaginatedResponseDto;

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
   * @param officeCode
   * @return
   */
  GenericResponseDto<OfficeDto> find( String officeCode );

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
  GenericResponseDto<Boolean> delete( String officeCode );
}
