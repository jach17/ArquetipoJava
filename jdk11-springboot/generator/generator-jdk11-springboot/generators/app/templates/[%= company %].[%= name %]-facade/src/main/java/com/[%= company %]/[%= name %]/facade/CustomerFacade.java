package com.[%= company %].[%= name %].facade;

import com.[%= company %].[%= name %].commons.dto.CustomerDto;
import com.[%= company %].[%= name %].commons.request.PaginatedRequestDto;
import com.[%= company %].[%= name %].commons.response.PaginatedResponseDto;

/**
 * @author guillermo.segura@axity.com
 */
public interface CustomerFacade
{
  /**
   * Consulta los clientes de manera paginada
   * 
   * @param limit
   * @param offset
   * @return
   */
  PaginatedResponseDto<CustomerDto> findCustomers( PaginatedRequestDto request );

  /**
   * Consulta un cliente por número
   * 
   * @param customerNumber
   * @return
   */
  CustomerDto findCustomer( Long customerNumber );

  /**
   * Alta de clientes
   * 
   * @param customer
   * @return
   */
  CustomerDto create( CustomerDto customer );
}
