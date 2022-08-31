package com.axity.arquetipo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.axity.arquetipo.commons.dto.OfficeDto;
import com.axity.arquetipo.persistence.redis.StringRedisRepository;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author guillermo.segura@axity.com
 */
@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class OfficeControllerIntegrationTest
{
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private StringRedisRepository redis;
  
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
       
    var gson = new GsonBuilder().create();
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.post( "/api/offices"  )
            .content(gson.toJson(office))
            .accept( MediaType.APPLICATION_JSON )
            .contentType( MediaType.APPLICATION_JSON ))
        .andExpect( status().isCreated() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body.id" ).value( 100 ) ).andReturn();

    assertNotNull( result );
  }
}
