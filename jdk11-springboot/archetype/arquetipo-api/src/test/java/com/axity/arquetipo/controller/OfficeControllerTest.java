package com.axity.arquetipo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.axity.arquetipo.commons.dto.OfficeDto;
import com.axity.arquetipo.commons.request.PaginatedRequestDto;
import com.axity.arquetipo.commons.response.GenericResponseDto;
import com.axity.arquetipo.commons.response.PaginatedResponseDto;
import com.axity.arquetipo.facade.OfficeFacade;
import com.axity.arquetipo.persistence.redis.StringRedisRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author guillermo.segura@axity.com
 */
@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class OfficeControllerTest
{

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OfficeFacade officeFacade;

  @MockBean
  private StringRedisRepository redis;

  @BeforeEach
  void setUp()
  {
    when( this.redis.hasKey( anyString() ) ).thenReturn( false );
  }

  /**
   * Test method for {@link com.axity.arquetipo.controller.OfficeController#findOffices(int, int)}.
   * 
   * @throws Exception
   */
  @Test
  void testFindOffices() throws Exception
  {
    int pageSize = 20;

    var data = new ArrayList<OfficeDto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.createOffice( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<OfficeDto>( 0, pageSize, 50, data );
    when( this.officeFacade.findOffices( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/offices?limit=20&offset=0" ) )
        .andExpect( status().isOk() ).andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.page" ).value( "0" ) ).andExpect( jsonPath( "$.size" ).value( "20" ) )
        .andExpect( jsonPath( "$.data" ).isArray() ).andExpect( jsonPath( "$.data[0].id" ).value( 1 ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for {@link com.axity.arquetipo.controller.OfficeController#findOffice(java.lang.String)}.
   */
  @ParameterizedTest()
  @ValueSource(booleans = { true, false })
  void testFindOffice( boolean exists ) throws Exception
  {
    var office = this.createOffice( 1 );
    var generic = new GenericResponseDto<>( office );

    if( exists )
    {
      when( this.redis.hasKey( anyString() ) ).thenReturn( true );
      var gson = new GsonBuilder().create();
      when( this.redis.get( anyString() ) ).thenReturn( gson.toJson( generic ) );
    }
    else
    {
      when( this.officeFacade.find( anyInt() ) ).thenReturn( generic );
    }

    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/offices/1" ) ).andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) ).andExpect( jsonPath( "$.body.id" ).value( 1 ) )
        .andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for {@link com.axity.arquetipo.controller.OfficeController#findOffice(java.lang.String)}.
   */
  @Test
  void testFindOffice_notExists() throws Exception
  {
    when( this.officeFacade.find( anyInt() ) ).thenReturn( null );

    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/offices/99999" ) )
        .andExpect( status().isNoContent() ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.axity.arquetipo.controller.OfficeController#create(com.axity.arquetipo.commons.dto.OfficeDto)}.
   */
  @Test
  void testCreate() throws Exception
  {
    var office = new OfficeDto();
    office.setCity( "CDMX" );
    office.setTerritory( "LATAM" );
    office.setState( "CDMX" );
    office.setAddressLine1( "Address" );
    office.setAddressLine2( "Address" );
    office.setCountry( "Mexico" );
    office.setPhone( "5555555" );
    office.setPostalCode( "55555" );

    var generic = new GenericResponseDto<>( office );
    generic.getBody().setId( 1 );
    when( this.officeFacade.create( any( OfficeDto.class ) ) ).thenReturn( generic );

    Gson gson = new GsonBuilder().create();

    MvcResult result = mockMvc
        .perform( MockMvcRequestBuilders.post( "/api/offices" ).content( gson.toJson( office ) )
            .accept( MediaType.APPLICATION_JSON ).contentType( MediaType.APPLICATION_JSON ) )
        .andExpect( status().isCreated() ).andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body.id" ).value( 1 ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.axity.arquetipo.controller.OfficeController#update(java.lang.String, com.axity.arquetipo.commons.dto.OfficeDto)}.
   */
  @ParameterizedTest()
  @ValueSource(booleans = { true, false })
  void testUpdate( boolean exists ) throws Exception
  {
    var office = this.createOffice( 1 );
    var generic = new GenericResponseDto<>( exists );

    when( this.officeFacade.update( any( OfficeDto.class ) ) ).thenReturn( generic );

    Gson gson = new GsonBuilder().create();

    MvcResult result = mockMvc
        .perform( MockMvcRequestBuilders.put( "/api/offices/1" ).content( gson.toJson( office ) )
            .accept( MediaType.APPLICATION_JSON ).contentType( MediaType.APPLICATION_JSON ) )
        .andExpect( status().isOk() ).andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body" ).value( exists ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for {@link com.axity.arquetipo.controller.OfficeController#delete(java.lang.String)}.
   */
  @ParameterizedTest()
  @ValueSource(booleans = { true, false })
  void testDelete( boolean exists ) throws Exception
  {
    var generic = new GenericResponseDto<>( exists );
    when( this.officeFacade.delete( anyInt() ) ).thenReturn( generic );

    MvcResult result = mockMvc
        .perform( MockMvcRequestBuilders.delete( "/api/offices/1" ).accept( MediaType.APPLICATION_JSON ) )
        .andExpect( status().isOk() ).andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body" ).value( exists ) ).andReturn();

    assertNotNull( result );
  }

  @Test
  void testPing() throws Exception
  {
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/offices/ping" ) ).andExpect( status().isOk() )
        .andReturn();

    assertNotNull( result );
    log.info( result.getResponse().getContentAsString() );
  }

  private OfficeDto createOffice( int i )
  {
    var office = new OfficeDto();
    office.setId( 1 );
    return office;
  }
}
