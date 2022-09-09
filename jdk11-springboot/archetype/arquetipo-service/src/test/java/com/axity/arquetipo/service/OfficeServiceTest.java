package com.axity.arquetipo.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.axity.arquetipo.commons.dto.OfficeDto;
import com.axity.arquetipo.commons.enums.ErrorCode;
import com.axity.arquetipo.commons.exception.BusinessException;
import com.axity.arquetipo.commons.request.MessageDto;
import com.axity.arquetipo.commons.request.PaginatedRequestDto;
import com.axity.arquetipo.commons.request.graphql.OfficeQueryDto;
import com.axity.arquetipo.commons.response.graphql.OfficeGraphQLDto;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author guillermo.segura@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Slf4j
class OfficeServiceTest
{
  @Autowired
  private OfficeService officeService;

  @MockBean
  private KafkaTemplate<Object, Object> template;

  /**
   * Método para validar que se consulte las oficinas de forma paginada
   */
  @Test
  void testFindOffices()
  {
    var request = new PaginatedRequestDto();
    request.setLimit( 5 );
    request.setOffset( 0 );
    var offices = this.officeService.findOffices( request );

    log.info( "Response: {}", offices );

    assertNotNull( offices );
    assertNotNull( offices.getData() );
    assertFalse( offices.getData().isEmpty() );
    assertEquals( 7, offices.getRegistries() );
  }

  /**
   * Prueba para validar la consulta de las oficinas por código
   * 
   * @param officeCode
   */
  @ParameterizedTest
  @ValueSource(ints = { 1, 2, 3, 4, 5, 6, 7 })
  void testFind( Integer officeId )
  {
    var office = this.officeService.find( officeId );
    assertNotNull( office );
    log.info( "Response: {}", office );
  }

  /**
   * Prueba para validar que regrese nulo si no existe la entidad de oficina
   */
  @Test
  void testFind_NotExists()
  {
    var office = this.officeService.find( 99999 );
    assertNull( office );
  }

  /**
   * Test method for
   * {@link com.axity.arquetipo.service.impl.OfficeServiceImpl#create(com.axity.arquetipo.commons.dto.OfficeDto)}.
   */
  @Test
  void testCreate()
  {
    var dto = new OfficeDto();
    dto.setCountry( "México" );
    dto.setTerritory( "LATAM" );
    dto.setCity( "CDMX" );
    dto.setAddressLine1( "Adress 1" );
    dto.setAddressLine2( "Adress 2" );
    dto.setState( "CDMX" );
    dto.setPhone( "+52 55 5555 5555" );
    dto.setPostalCode( "11200" );

    var response = this.officeService.create( dto );
    log.info( response.toString() );

    assertNotNull( response );
    assertEquals( 0, response.getHeader().getCode() );
    assertNotNull( response.getBody() );

    this.officeService.delete( dto.getId() );
  }

  /**
   * Prueba para validar la actualización
   */
  @Test
  void testUpdate()
  {
    var office = this.officeService.find( 1 ).getBody();
    var address = "new Address";
    office.setAddressLine1( address );
    var response = this.officeService.update( office );

    assertNotNull( response );
    assertEquals( 0, response.getHeader().getCode() );
    assertTrue( response.getBody() );
    office = this.officeService.find( 1 ).getBody();

    assertEquals( address, office.getAddressLine1() );
  }

  /**
   * Prueba para validar el error cuando no existe una oficina y se trata de editar
   */
  @Test
  void testUpdate_NotFound()
  {
    var office = new OfficeDto();
    office.setId( 999999 );
    var ex = assertThrows( BusinessException.class, () -> this.officeService.update( office ) );

    assertEquals( ErrorCode.OFFICE_NOT_FOUND.getCode(), ex.getCode() );
  }

  /**
   * Test method for {@link com.axity.arquetipo.service.impl.OfficeServiceImpl#delete(java.lang.String)}.
   */
  @Test
  void testDeleteNotFound()
  {
    var ex = assertThrows( BusinessException.class, () -> this.officeService.delete( 999999 ) );
    assertEquals( ErrorCode.OFFICE_NOT_FOUND.getCode(), ex.getCode() );
  }

  @Test
  void testFindGraphQL_empty()
  {
    var query = new OfficeQueryDto();
    var list = this.officeService.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }

  @ParameterizedTest()
  @ValueSource(ints = { 1, 2, 3, 4, 5, 6, 7 })
  void testFindGraphQL_officeCode( Integer officeId )
  {
    var query = new OfficeQueryDto();
    query.setQuery( new OfficeGraphQLDto() );
    query.getQuery().setId( officeId );
    var list = this.officeService.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }

  @ParameterizedTest()
  @ValueSource(strings = { "San Francisco", "Boston", "London" })
  void testFindGraphQL_city( String city )
  {
    var query = new OfficeQueryDto();
    query.setQuery( new OfficeGraphQLDto() );
    query.getQuery().setCity( city );
    var list = this.officeService.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }

  @ParameterizedTest()
  @ValueSource(strings = { "+1", "+33", "650 219 4782" })
  void testFindGraphQL_phone( String phone )
  {
    var query = new OfficeQueryDto();
    query.setQuery( new OfficeGraphQLDto() );
    query.getQuery().setPhone( phone );
    var list = this.officeService.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }

  @ParameterizedTest()
  @ValueSource(strings = { "Market Street", "Wentworth" })
  void testFindGraphQL_addressLine1( String addressLine1 )
  {
    var query = new OfficeQueryDto();
    query.setQuery( new OfficeGraphQLDto() );
    query.getQuery().setAddressLine1( addressLine1 );
    var list = this.officeService.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }

  @ParameterizedTest()
  @ValueSource(strings = { "Suite", "Floor" })
  void testFindGraphQL_addressLine2( String addressLine2 )
  {
    var query = new OfficeQueryDto();
    query.setQuery( new OfficeGraphQLDto() );
    query.getQuery().setAddressLine2( addressLine2 );
    var list = this.officeService.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }

  @ParameterizedTest()
  @ValueSource(strings = { "CA", "NY", "MA" })
  void testFindGraphQL_state( String state )
  {
    var query = new OfficeQueryDto();
    query.setQuery( new OfficeGraphQLDto() );
    query.getQuery().setState( state );
    var list = this.officeService.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }

  @ParameterizedTest()
  @ValueSource(strings = { "USA", "France", "Australia" })
  void testFindGraphQL_country( String country )
  {
    var query = new OfficeQueryDto();
    query.setQuery( new OfficeGraphQLDto() );
    query.getQuery().setCountry( country );
    var list = this.officeService.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }

  @ParameterizedTest()
  @ValueSource(strings = { "94080", "02107" })
  void testFindGraphQL_postalCode( String postalCode )
  {
    var query = new OfficeQueryDto();
    query.setQuery( new OfficeGraphQLDto() );
    query.getQuery().setPostalCode( postalCode );
    var list = this.officeService.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }

  @ParameterizedTest()
  @ValueSource(strings = { "NA", "EMEA", "APAC", "Japan" })
  void testFindGraphQL_territory( String territory )
  {
    var query = new OfficeQueryDto();
    query.setQuery( new OfficeGraphQLDto() );
    query.getQuery().setTerritory( territory );
    var list = this.officeService.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }

  @ParameterizedTest()
  @CsvSource({ "NA,0,1", "EMEA,0,1", "NA,0,0" })
  void testFindGraphQL_pageable( String territory, int page, int size )
  {
    var query = new OfficeQueryDto();
    query.setQuery( new OfficeGraphQLDto() );
    query.getQuery().setTerritory( territory );
    query.setPage( page );
    query.setSize( size );
    var list = this.officeService.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }

  @Test
  void testProcessMessage()
  {

    var dto = new OfficeDto();
    dto.setCountry( "México" );
    dto.setTerritory( "LATAM" );
    dto.setCity( "CDMX" );
    dto.setAddressLine1( "Adress 1" );
    dto.setAddressLine2( "Adress 2" );
    dto.setState( "CDMX" );
    dto.setPhone( "+52 55 5555 5555" );
    dto.setPostalCode( "11200" );

    var gson = new GsonBuilder().create();
    var message = new MessageDto();
    message.setMessage( "Lorem ipsum dolor sit amet" );
    message.setJson( gson.toJson( dto ) );

    assertDoesNotThrow( () -> this.officeService.processMessage( gson.toJson( message ) ) );
  }
}
