package mx.com.axity.arquetipo.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import mx.com.axity.arquetipo.commons.aspectj.JsonResponseInterceptor;
import mx.com.axity.arquetipo.commons.dto.CustomerDto;
import mx.com.axity.arquetipo.commons.request.PaginatedRequestDto;
import mx.com.axity.arquetipo.commons.response.PaginatedResponseDto;
import mx.com.axity.arquetipo.facade.CustomerFacade;

/**
 * Controlador de clientes
 * 
 * @author guillermo.segura@axity.com
 */
@RestController
@RequestMapping("/api/customers")
@CrossOrigin
public class CustomerController
{
  @Autowired
  private CustomerFacade customerFacade;

  /**
   * Consulta de clientes de manera paginada
   * 
   * @param limit
   * @param offset
   * @return
   */
  @JsonResponseInterceptor
  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Customers", summary = "Consulta los clientes")
  public ResponseEntity<PaginatedResponseDto<CustomerDto>> findCustomers(
      @RequestParam(name = "limit", defaultValue = "50", required = false) int limit,
      @RequestParam(name = "offset", defaultValue = "0", required = false) int offset )
  {
    var result = this.customerFacade.findCustomers( new PaginatedRequestDto( limit, offset ) );

    return ResponseEntity.ok().body( result );
  }

  /***
   * Obtiene un cliente por su número de cliente
   * 
   * @param customerNumber
   * @return
   */
  @JsonResponseInterceptor
  @GetMapping(path = "/{customerNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Customers", summary = "Consulta de cliente")
  public ResponseEntity<CustomerDto> findCustomer( @PathParam("customerNumber") Long customerNumber )
  {
    var result = this.customerFacade.findCustomer( customerNumber );
    HttpStatus status = HttpStatus.OK;
    if( result == null )
    {
      status = HttpStatus.NO_CONTENT;
    }
    return new ResponseEntity<>( result, status );
  }

  /**
   * Crea una lista
   * 
   * @param customer
   * @return
   */
  @JsonResponseInterceptor
  @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Customers", summary = "Alta de cliente")
  public ResponseEntity<CustomerDto> create( @Valid
  @RequestBody CustomerDto customer )
  {
    var result = this.customerFacade.create( customer );
    return new ResponseEntity<>( result, HttpStatus.CREATED );
  }

}
