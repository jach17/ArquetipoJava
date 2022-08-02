package com.axity.arquetipo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.axity.arquetipo.commons.dto.OfficeDto;
import com.axity.arquetipo.commons.enums.ErrorCode;
import com.axity.arquetipo.commons.exception.BusinessException;
import com.axity.arquetipo.commons.request.PaginatedRequestDto;
import com.axity.arquetipo.commons.request.graphql.OfficeQueryDto;
import com.axity.arquetipo.commons.response.graphql.OfficeGraphQLDto;

/**
 * @author guillermo.segura@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class OfficeServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( OfficeServiceTest.class );

  @Autowired
  private OfficeService officeService;

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

    LOG.info( "Response: {}", offices );

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
  @ValueSource(strings = { "1", "2", "3", "4", "5", "6", "7" })
  void testFind( String officeCode )
  {
    var office = this.officeService.find( officeCode );
    assertNotNull( office );
    LOG.info( "Response: {}", office );
  }

  /**
   * Prueba para validar que regrese nulo si no existe la entidad de oficina
   */
  @Test
  void testFind_NotExists()
  {
    var office = this.officeService.find( "999999" );
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
    dto.setOfficeCode( "8" );
    dto.setCountry( "México" );
    dto.setTerritory( "LATAM" );
    dto.setCity( "CDMX" );
    dto.setAddressLine1( "Adress 1" );
    dto.setAddressLine2( "Adress 2" );
    dto.setState( "CDMX" );
    dto.setPhone( "+52 55 5555 5555" );
    dto.setPostalCode( "11200" );

    var response = this.officeService.create( dto );
    assertNotNull( response );
    assertEquals( 0, response.getHeader().getCode() );
    assertNotNull( response.getBody() );

    this.officeService.delete( dto.getOfficeCode() );
  }

  @Test
  void testCreate_ErrorExists()
  {
    var dto = new OfficeDto();
    dto.setOfficeCode( "1" );
    dto.setCountry( "México" );
    dto.setTerritory( "LATAM" );
    dto.setCity( "CDMX" );
    dto.setAddressLine1( "Adress 1" );
    dto.setAddressLine2( "Adress 2" );
    dto.setState( "CDMX" );
    dto.setPhone( "+52 55 5555 5555" );
    dto.setPostalCode( "11200" );

    var ex = assertThrows( BusinessException.class, () -> this.officeService.create( dto ) );
    assertNotNull( ex );
    assertEquals( ErrorCode.OFFICE_ALREADY_EXISTS.getCode(), ex.getCode() );
  }

  /**
   * Prueba para validar la actualización
   */
  @Test
  void testUpdate()
  {
    var office = this.officeService.find( "1" ).getBody();
    var address = "new Address";
    office.setAddressLine1( address );
    var response = this.officeService.update( office );

    assertNotNull( response );
    assertEquals( 0, response.getHeader().getCode() );
    assertTrue( response.getBody() );
    office = this.officeService.find( "1" ).getBody();

    assertEquals( address, office.getAddressLine1() );
  }

  /**
   * Prueba para validar el error cuando no existe una oficina y se trata de editar
   */
  @Test
  void testUpdate_NotFound()
  {
    var office = new OfficeDto();
    office.setOfficeCode( "999999" );
    var ex = assertThrows( BusinessException.class, () -> this.officeService.update( office ) );

    assertEquals( ErrorCode.OFFICE_NOT_FOUND.getCode(), ex.getCode() );
  }

  /**
   * Test method for {@link com.axity.arquetipo.service.impl.OfficeServiceImpl#delete(java.lang.String)}.
   */
  @Test
  void testDeleteNotFound()
  {
    var ex = assertThrows( BusinessException.class, () -> this.officeService.delete( "9999" ) );
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
  @ValueSource(strings = { "1", "2", "3", "4", "5", "6", "7" })
  void testFindGraphQL_officeCode( String officeCode )
  {
    var query = new OfficeQueryDto();
    query.setQuery( new OfficeGraphQLDto() );
    query.getQuery().setOfficeCode( officeCode );
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
}
