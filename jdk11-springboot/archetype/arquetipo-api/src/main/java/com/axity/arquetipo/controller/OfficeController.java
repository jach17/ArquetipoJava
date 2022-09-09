package com.axity.arquetipo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axity.arquetipo.commons.aspectj.JsonResponseInterceptor;
import com.axity.arquetipo.commons.dto.OfficeDto;
import com.axity.arquetipo.commons.request.PaginatedRequestDto;
import com.axity.arquetipo.commons.response.GenericResponseDto;
import com.axity.arquetipo.commons.response.PaginatedResponseDto;
import com.axity.arquetipo.facade.OfficeFacade;
import com.axity.arquetipo.persistence.redis.StringRedisRepository;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author guillermo.segura@axity.com
 */
@RestController
@RequestMapping("/api/offices")
@Slf4j
public class OfficeController
{
  @Autowired
  private OfficeFacade officeFacade;

  @Autowired
  private StringRedisRepository redis;

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
  @GetMapping(path = "/{officeId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Offices", description = "Consulta la oficina por el officeCode", summary = "Consulta la oficina por el officeCode")
  public ResponseEntity<GenericResponseDto<OfficeDto>> findOffice( @PathVariable("officeId") Integer officeId )
  {

    String key = getOfficeKey( officeId );

    var gson = new GsonBuilder().create();
    GenericResponseDto<OfficeDto> result = null;
    if( redis.hasKey( key ) )
    {
      var json = this.redis.get( key );

      result = gson.fromJson( json, new TypeToken<GenericResponseDto<OfficeDto>>()
      {
      }.getType() );
    }
    else
    {
      result = this.officeFacade.find( officeId );

      String json = gson.toJson( result );
      this.redis.add( key, json );
    }

    HttpStatus status = HttpStatus.OK;
    if( result == null )
    {
      status = HttpStatus.NO_CONTENT;
    }
    return new ResponseEntity<>( result, status );
  }

  private String getOfficeKey( Integer officeId )
  {
    return new StringBuilder().append( "Offices.byOfficeCode:" ).append( officeId ).toString();
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
  public ResponseEntity<GenericResponseDto<OfficeDto>> create( @Valid @RequestBody OfficeDto office )
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
  @PutMapping(path = "/{officeId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Offices", description = "Edita una oficina", summary = "Edita una oficina")
  public ResponseEntity<GenericResponseDto<Boolean>> update( @PathVariable("officeId") Integer officeId,
      @RequestBody OfficeDto office )
  {
    office.setId( officeId );
    var result = this.officeFacade.update( office );

    if( Boolean.TRUE.equals( result.getBody()) )
    {
      this.redis.delete( this.getOfficeKey( officeId ) );
    }

    return ResponseEntity.ok( result );
  }

  /**
   * Elimina una oficina
   * 
   * @param officeCode
   * @return
   */
  @JsonResponseInterceptor
  @DeleteMapping(path = "/{officeId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Offices", description = "Elimina una oficina", summary = "Elimina una oficina")
  public ResponseEntity<GenericResponseDto<Boolean>> delete( @PathVariable("officeId") Integer officeId )
  {
    var result = this.officeFacade.delete( officeId );
    if( Boolean.TRUE.equals( result.getBody()) )
    {
      this.redis.delete( this.getOfficeKey( officeId ) );
    }
    return ResponseEntity.ok( result );
  }

  /**
   * Ping
   * 
   * @return
   */
  @JsonResponseInterceptor
  @GetMapping(path = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Oficinas", summary = "Ping")
  public ResponseEntity<GenericResponseDto<String>> ping()
  {
    log.debug( "ping" );
    return ResponseEntity.ok( new GenericResponseDto<>( "pong" ) );
  }
}
