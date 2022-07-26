package mx.com.axity.arquetipo.service;

import mx.com.axity.arquetipo.commons.dto.CustomerDto;
import mx.com.axity.arquetipo.commons.request.PaginatedRequestDto;
import mx.com.axity.arquetipo.commons.response.PaginatedResponseDto;

/**
 * @author guillermo.segura@axity.com
 */
public interface CustomerService
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
   * Consulta el cliente por número
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
