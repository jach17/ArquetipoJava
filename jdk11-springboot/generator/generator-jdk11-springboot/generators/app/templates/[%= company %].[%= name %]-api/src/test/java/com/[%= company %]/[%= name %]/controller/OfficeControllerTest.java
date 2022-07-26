package com.[%= company %].[%= name %].controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import lombok.extern.slf4j.Slf4j;
import com.[%= company %].[%= name %].commons.dto.OfficeDto;
import com.[%= company %].[%= name %].commons.request.PaginatedRequestDto;
import com.[%= company %].[%= name %].commons.response.PaginatedResponseDto;
import com.[%= company %].[%= name %].facade.OfficeFacade;

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

  /**
   * Test method for {@link com.[%= company %].[%= name %].controller.OfficeController#findOffices(int, int)}.
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
    when( this.officeFacade.findOffices( any(PaginatedRequestDto.class) ) ).thenReturn( paginated );
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/offices?limit=20&offset=0" ) )
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.page" ).value( "0" ) )
        .andExpect( jsonPath( "$.size" ).value( "20" ) )
        .andExpect( jsonPath( "$.data" ).isArray() )
        .andExpect( jsonPath( "$.data[0].officeId" ).value( "1" ) )
        .andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for {@link com.[%= company %].[%= name %].controller.OfficeController#findOffice(java.lang.String)}.
   */
  @Test
  void testFindOffice()
  {
    fail( "Not yet implemented" );
  }

  /**
   * Test method for
   * {@link com.[%= company %].[%= name %].controller.OfficeController#create(com.[%= company %].[%= name %].commons.dto.OfficeDto)}.
   */
  @Test
  void testCreate()
  {
    fail( "Not yet implemented" );
  }

  /**
   * Test method for
   * {@link com.[%= company %].[%= name %].controller.OfficeController#update(java.lang.String, com.[%= company %].[%= name %].commons.dto.OfficeDto)}.
   */
  @Test
  void testUpdate()
  {
    fail( "Not yet implemented" );
  }

  /**
   * Test method for {@link com.[%= company %].[%= name %].controller.OfficeController#delete(java.lang.String)}.
   */
  @Test
  void testDelete()
  {
    fail( "Not yet implemented" );
  }

  private OfficeDto createOffice( int i )
  {
    var office = new OfficeDto();
    office.setOfficeCode( String.valueOf( i ) );
    return office;
  }
}
