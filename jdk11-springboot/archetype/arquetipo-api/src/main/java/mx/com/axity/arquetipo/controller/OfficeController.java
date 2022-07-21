package mx.com.axity.arquetipo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import mx.com.axity.arquetipo.commons.aspectj.JsonResponseInterceptor;
import mx.com.axity.arquetipo.commons.dto.OfficeDto;
import mx.com.axity.arquetipo.commons.request.PaginatedRequestDto;
import mx.com.axity.arquetipo.commons.response.GenericResponseDto;
import mx.com.axity.arquetipo.commons.response.PaginatedResponseDto;
import mx.com.axity.arquetipo.facade.OfficeFacade;

/**
 * @author guillermo.segura@axity.com
 */
@RestController
@RequestMapping("/api/offices")
@CrossOrigin
public class OfficeController
{
  @Autowired
  private OfficeFacade officeFacade;

  /***
   * Consulta las oficinas
   * 
   * @param limit
   * @param offset
   * @return
   */
  @JsonResponseInterceptor
  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Offices", summary = "Consulta las oficinas")
  public ResponseEntity<PaginatedResponseDto<OfficeDto>> findOffices(
      @RequestParam(name = "limit", defaultValue = "50", required = false) int limit,
      @RequestParam(name = "offset", defaultValue = "0", required = false) int offset )
  {
    var result = this.officeFacade.findOffices( new PaginatedRequestDto( limit, offset ) );
    return ResponseEntity.ok( result );
  }

  /**
   * Consula de oficina por id
   * 
   * @param officeCode
   * @return
   */
  @JsonResponseInterceptor
  @GetMapping(path = "/{officeCode}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Offices", description = "Consulta la oficina por el officeCode", summary = "Consulta la oficina por el officeCode")
  public ResponseEntity<GenericResponseDto<OfficeDto>> findOffice( @PathVariable("officeCode") String officeCode )
  {
    var result = this.officeFacade.find( officeCode );
    HttpStatus status = HttpStatus.OK;
    if( result == null )
    {
      status = HttpStatus.NO_CONTENT;
    }
    return new ResponseEntity<>( result, status );
  }

  /**
   * Crea una oficina
   * 
   * @param office
   * @return
   */
  @JsonResponseInterceptor
  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Offices", description = "Crea una oficina", summary = "Crea una oficina")
  public ResponseEntity<GenericResponseDto<OfficeDto>> create( @RequestBody OfficeDto office )
  {
    var result = this.officeFacade.create( office );
    return new ResponseEntity<>( result, HttpStatus.CREATED );
  }

  /**
   * Actauliza una oficina
   * 
   * @param officeCode
   * @param office
   * @return
   */
  @JsonResponseInterceptor
  @PutMapping(path = "/{officeCode}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Offices", description = "Edita una oficina", summary = "Edita una oficina")
  public ResponseEntity<GenericResponseDto<Boolean>> update( @PathVariable("officeCode") String officeCode,
      @RequestBody OfficeDto office )
  {
    office.setOfficeCode( officeCode );
    var result = this.officeFacade.update( office );
    return ResponseEntity.ok( result );
  }

  /**
   * Elimina una oficina
   * 
   * @param officeCode
   * @return
   */
  @JsonResponseInterceptor
  @DeleteMapping(path = "/{officeCode}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Offices", description = "Elimina una oficina", summary = "Elimina una oficina")
  public ResponseEntity<GenericResponseDto<Boolean>> delete( @PathVariable("officeCode") String officeCode )
  {
    var result = this.officeFacade.delete( officeCode );
    return ResponseEntity.ok( result );
  }
}
