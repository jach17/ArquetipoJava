package com.[%= companylower %].[%= namelower %].controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto;
import com.[%= companylower %].[%= namelower %].commons.request.PaginatedRequestDto;
import com.[%= companylower %].[%= namelower %].commons.response.GenericResponseDto;
import com.[%= companylower %].[%= namelower %].commons.response.PaginatedResponseDto;
import com.[%= companylower %].[%= namelower %].facade.[%= namecamel %]Facade;
import com.[%= companylower %].[%= namelower %].persistence.redis.StringRedisRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * [%= namecamel %] Controller Test class
 * 
 * @author [%= username %]
 */
@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class [%= namecamel %]ControllerTest
{

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private [%= namecamel %]Facade [%= namelower %]Facade;
  
  @MockBean
  private StringRedisRepository redis;
  
  @BeforeEach
  void setUp() {
    when(this.redis.hasKey( anyString() )).thenReturn( false );
  }

  /**
   * Test method for {@link com.[%= companylower %].[%= namelower %].controller.[%= namecamel %]Controller#find[%= namecamel %]s(int, int)}.
   * 
   * @throws Exception
   */
  @Test
  void testFind[%= namecamel %]s() throws Exception
  {
    int pageSize = 20;

    var data = new ArrayList<[%= namecamel %]Dto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.create[%= namecamel %]( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<[%= namecamel %]Dto>( 0, pageSize, 50, data );
    when( this.[%= namelower %]Facade.find[%= namecamel %]s( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/[%= namelower %]s?limit=20&offset=0" ) )
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.page" ).value( "0" ) )
        .andExpect( jsonPath( "$.size" ).value( "20" ) )
        .andExpect( jsonPath( "$.data" ).isArray() )
        .andExpect( jsonPath( "$.data[0].Id" ).value( 1 ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for {@link com.[%= companylower %].[%= namelower %].controller.[%= namecamel %]Controller#find[%= namecamel %](java.lang.String)}.
   * 
   * @throws Exception
   */
  @Test
  void testFind[%= namecamel %]() throws Exception
  {
    var [%= namelower %] = this.create[%= namecamel %]( 1 );
    var generic = new GenericResponseDto<>([%= namelower %]);
    when( this.[%= namelower %]Facade.find( anyInt() ) ).thenReturn( generic );
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/[%= namelower %]s/1" ) )
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body.Id" ).value( 1 ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.[%= companylower %].[%= namelower %].controller.[%= namecamel %]Controller#create(com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto)}.
   * 
   * @throws Exception
   */
  @Test
  void testCreate() throws Exception
  {
    var [%= namelower %] = this.create[%= namecamel %]( 9 );
    var generic = new GenericResponseDto<>([%= namelower %]);
    when(this.[%= namelower %]Facade.create( any( [%= namecamel %]Dto.class ) )).thenReturn( generic );
    
    Gson gson = new GsonBuilder().create();
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.post( "/api/[%= namelower %]s"  )
            .content(gson.toJson([%= namelower %]))
            .accept( MediaType.APPLICATION_JSON )
            .contentType( MediaType.APPLICATION_JSON ))
        .andExpect( status().isCreated() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body.[%= namelower %]Id" ).value( "9" ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.[%= companylower %].[%= namelower %].controller.[%= namecamel %]Controller#update(java.lang.String, com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto)}.
   * 
   * @throws Exception
   */
  @Test
  void testUpdate() throws Exception
  {
    var [%= namelower %] = this.create[%= namecamel %]( 1 );
    var generic = new GenericResponseDto<>(true);
    when(this.[%= namelower %]Facade.update( any( [%= namecamel %]Dto.class ) )).thenReturn( generic );
    
    Gson gson = new GsonBuilder().create();
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.put( "/api/[%= namelower %]s/1"  )
            .content(gson.toJson([%= namelower %]))
            .accept( MediaType.APPLICATION_JSON )
            .contentType( MediaType.APPLICATION_JSON ))
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body" ).value( "true" ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for {@link com.[%= companylower %].[%= namelower %].controller.[%= namecamel %]Controller#delete(java.lang.String)}.
   * 
   * @throws Exception
   */
  @Test
  void testDelete() throws Exception
  {
    var generic = new GenericResponseDto<>(true);
    when(this.[%= namelower %]Facade.delete( anyInt() )).thenReturn( generic );
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.delete( "/api/[%= namelower %]s/1"  )
            .accept( MediaType.APPLICATION_JSON ))
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body" ).value( "true" ) ).andReturn();

    assertNotNull( result );
  }

  private [%= namecamel %]Dto create[%= namecamel %]( int i )
  {
    var [%= namelower %] = new [%= namecamel %]Dto();
    [%= namelower %].setId( i );
    return [%= namelower %];
  }
}
