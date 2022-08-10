package com.[%= companylower %].[%= namelower %].service;

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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto;
import com.[%= companylower %].[%= namelower %].commons.enums.ErrorCode;
import com.[%= companylower %].[%= namelower %].commons.exception.BusinessException;
import com.[%= companylower %].[%= namelower %].commons.request.PaginatedRequestDto;
import com.[%= companylower %].[%= namelower %].commons.request.graphql.[%= namecamel %]QueryDto;
import com.[%= companylower %].[%= namelower %].commons.response.graphql.[%= namecamel %]GraphQLDto;

/**
 * Class [%= namecamel %]ServiceTest
 * 
 * @author [%= username %]
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class [%= namecamel %]ServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( [%= namecamel %]ServiceTest.class );

  @Autowired
  private [%= namecamel %]Service [%= namelower %]Service;

  @MockBean
  private KafkaTemplate<Object, Object> template;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFind[%= namecamel %]s()
  {
    var request = new PaginatedRequestDto();
    request.setLimit( 5 );
    request.setOffset( 0 );
    var [%= namelower %]s = this.[%= namelower %]Service.find[%= namecamel %]s( request );

    LOG.info( "Response: {}", [%= namelower %]s );

    assertNotNull( [%= namelower %]s );
    assertNotNull( [%= namelower %]s.getData() );
    assertFalse( [%= namelower %]s.getData().isEmpty() );
    assertEquals( 7, [%= namelower %]s.getRegistries() );
  }

  /**
   * Method to validate the search by id
   * 
   * @param [%= namelower %]Id
   */
  @ParameterizedTest
  @ValueSource(ints = { 1, 2, 3, 4, 5, 6, 7 })
  void testFind( Integer [%= namelower %]Id )
  {
    var [%= namelower %] = this.[%= namelower %]Service.find( [%= namelower %]Id );
    assertNotNull( [%= namelower %] );
    LOG.info( "Response: {}", [%= namelower %] );
  }

  /**
   * Method to validate the search by id inexistent
   */
  @Test
  void testFind_NotExists()
  {
    var [%= namelower %] = this.[%= namelower %]Service.find( 999999 );
    assertNull( [%= namelower %] );
  }

  /**
   * Test method for
   * {@link com.[%= companylower %].[%= namelower %].service.impl.[%= namecamel %]ServiceImpl#create(com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto)}.
   */
  @Test
  void testCreate()
  {
    var dto = new [%= namecamel %]Dto();
    dto.setCountry( "México" );
    dto.setTerritory( "LATAM" );
    dto.setCity( "CDMX" );
    dto.setAddressLine1( "Adress 1" );
    dto.setAddressLine2( "Adress 2" );
    dto.setState( "CDMX" );
    dto.setPhone( "+52 55 5555 5555" );
    dto.setPostalCode( "11200" );

    var response = this.[%= namelower %]Service.create( dto );
    assertNotNull( response );
    assertEquals( 0, response.getHeader().getCode() );
    assertNotNull( response.getBody() );

    this.[%= namelower %]Service.delete( dto.getId() );
  }

  /**
   * Method to validate update
   */
  @Test
  void testUpdate()
  {
    var [%= namelower %] = this.[%= namelower %]Service.find( 1 ).getBody();
    var address = "new Address";
    [%= namelower %].setAddressLine1( address );
    var response = this.[%= namelower %]Service.update( [%= namelower %] );

    assertNotNull( response );
    assertEquals( 0, response.getHeader().getCode() );
    assertTrue( response.getBody() );
    [%= namelower %] = this.[%= namelower %]Service.find( 1 ).getBody();

    assertEquals( address, [%= namelower %].getAddressLine1() );
  }

  /**
   * Method to validate an inexistent registry
   */
  @Test
  void testUpdate_NotFound()
  {
    var [%= namelower %] = new [%= namecamel %]Dto();
    [%= namelower %].setId(999999);
    var ex = assertThrows( BusinessException.class, () -> this.[%= namelower %]Service.update( [%= namelower %] ) );

    assertEquals( ErrorCode.[%= nameupper %]_NOT_FOUND.getCode(), ex.getCode() );
  }

  /**
   * Test method for {@link com.[%= companylower %].[%= namelower %].service.impl.[%= namecamel %]ServiceImpl#delete(java.lang.String)}.
   */
  @Test
  void testDeleteNotFound()
  {
    var ex = assertThrows( BusinessException.class, () -> this.[%= namelower %]Service.delete( 999999 ) );
    assertEquals( ErrorCode.[%= nameupper %]_NOT_FOUND.getCode(), ex.getCode() );
  }

  /**
   * Test method to validate a query by example with no data (all)
   */
  @Test
  void testFindGraphQL_empty()
  {
    var query = new [%= namecamel %]QueryDto();
    var list = this.[%= namelower %]Service.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }

  /**
   * Test method to validate a query by example with id
   */
  @ParameterizedTest()
  @ValueSource(ints = { 1, 2, 3, 4, 5, 6, 7 })
  void testFindGraphQL_[%= namelower %]Code( Integer [%= namelower %]Id )
  {
    var query = new [%= namecamel %]QueryDto();
    query.setQuery( new [%= namecamel %]GraphQLDto() );
    query.getQuery().setId( [%= namelower %]Id );
    var list = this.[%= namelower %]Service.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }

  /**
   * Test method to validate a query by example with city
   */
  @ParameterizedTest()
  @ValueSource(strings = { "San Francisco", "Boston", "London" })
  void testFindGraphQL_city( String city )
  {
    var query = new [%= namecamel %]QueryDto();
    query.setQuery( new [%= namecamel %]GraphQLDto() );
    query.getQuery().setCity( city );
    var list = this.[%= namelower %]Service.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }

  /**
   * Test method to validate a query by example with phone
   */
  @ParameterizedTest()
  @ValueSource(strings = { "+1", "+33", "650 219 4782" })
  void testFindGraphQL_phone( String phone )
  {
    var query = new [%= namecamel %]QueryDto();
    query.setQuery( new [%= namecamel %]GraphQLDto() );
    query.getQuery().setPhone( phone );
    var list = this.[%= namelower %]Service.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }

  /**
   * Test method to validate a query by example with addressLine1
   */
  @ParameterizedTest()
  @ValueSource(strings = { "Market Street", "Wentworth" })
  void testFindGraphQL_addressLine1( String addressLine1 )
  {
    var query = new [%= namecamel %]QueryDto();
    query.setQuery( new [%= namecamel %]GraphQLDto() );
    query.getQuery().setAddressLine1( addressLine1 );
    var list = this.[%= namelower %]Service.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }

  /**
   * Test method to validate a query by example with addressLine2
   */
  @ParameterizedTest()
  @ValueSource(strings = { "Suite", "Floor" })
  void testFindGraphQL_addressLine2( String addressLine2 )
  {
    var query = new [%= namecamel %]QueryDto();
    query.setQuery( new [%= namecamel %]GraphQLDto() );
    query.getQuery().setAddressLine2( addressLine2 );
    var list = this.[%= namelower %]Service.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }
  
  /**
   * Test method to validate a query by example with state
   */
  @ParameterizedTest()
  @ValueSource(strings = { "CA", "NY", "MA" })
  void testFindGraphQL_state( String state )
  {
    var query = new [%= namecamel %]QueryDto();
    query.setQuery( new [%= namecamel %]GraphQLDto() );
    query.getQuery().setState( state );
    var list = this.[%= namelower %]Service.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }
  
  /**
   * Test method to validate a query by example with country
   */
  @ParameterizedTest()
  @ValueSource(strings = { "USA", "France", "Australia" })
  void testFindGraphQL_country( String country )
  {
    var query = new [%= namecamel %]QueryDto();
    query.setQuery( new [%= namecamel %]GraphQLDto() );
    query.getQuery().setCountry( country );
    var list = this.[%= namelower %]Service.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }
  
  /**
   * Test method to validate a query by example with postalCode
   */
  @ParameterizedTest()
  @ValueSource(strings = { "94080", "02107" })
  void testFindGraphQL_postalCode( String postalCode )
  {
    var query = new [%= namecamel %]QueryDto();
    query.setQuery( new [%= namecamel %]GraphQLDto() );
    query.getQuery().setPostalCode( postalCode );
    var list = this.[%= namelower %]Service.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }
  
  /**
   * Test method to validate a query by example with territory
   */
  @ParameterizedTest()
  @ValueSource(strings = { "NA", "EMEA", "APAC", "Japan" })
  void testFindGraphQL_territory( String territory )
  {
    var query = new [%= namecamel %]QueryDto();
    query.setQuery( new [%= namecamel %]GraphQLDto() );
    query.getQuery().setTerritory( territory );
    var list = this.[%= namelower %]Service.findGraphQL( query, null );
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }
}
