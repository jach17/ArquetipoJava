package com.axity.arquetipo.facade;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.axity.arquetipo.commons.dto.OfficeDto;
import com.axity.arquetipo.commons.request.PaginatedRequestDto;
import com.axity.arquetipo.commons.request.graphql.OfficeQueryDto;
import com.axity.arquetipo.commons.response.GenericResponseDto;
import com.axity.arquetipo.commons.response.PaginatedResponseDto;
import com.axity.arquetipo.commons.response.graphql.OfficeGraphQLDto;
import com.axity.arquetipo.facade.impl.OfficeFacadeImpl;
import com.axity.arquetipo.service.OfficeService;

import graphql.schema.DataFetchingEnvironment;

/**
 * @author guillermo.segura@axity.com
 */
class OfficeFacadeTest
{
  private OfficeFacade officeFacade;
  private OfficeService officeService;

  @BeforeEach
  public void setUp()
  {
    this.officeFacade = new OfficeFacadeImpl();

    this.officeService = mock( OfficeService.class );
    ReflectionTestUtils.setField( this.officeFacade, "officeService", this.officeService );
  }

  /**
   * Test method for
   * {@link com.axity.arquetipo.facade.impl.OfficeFacadeImpl#findOffices(com.axity.arquetipo.commons.request.PaginatedRequestDto)}.
   */
  @Test
  void testFindOffices()
  {
    int pageSize = 20;

    var data = new ArrayList<OfficeDto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.createOffice( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<OfficeDto>( 0, pageSize, 50, data );
    when( this.officeService.findOffices( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    var result = this.officeFacade.findOffices( new PaginatedRequestDto( pageSize, 0 ) );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.axity.arquetipo.facade.impl.OfficeFacadeImpl#find(java.lang.String)}.
   */
  @Test
  void testFind()
  {
    var response = new GenericResponseDto<OfficeDto>( this.createOffice( 1 ) );
    when( this.officeService.find( any( Integer.class ) ) ).thenReturn( response );

    var result = this.officeFacade.find( 1 );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.axity.arquetipo.facade.impl.OfficeFacadeImpl#create(com.axity.arquetipo.commons.dto.OfficeDto)}.
   */
  @Test
  void testCreate()
  {
    var office = this.createOffice( 8 );
    var response = new GenericResponseDto<>( office );
    when( this.officeService.create( any( OfficeDto.class ) ) ).thenReturn( response );

    var result = this.officeFacade.create( office );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.axity.arquetipo.facade.impl.OfficeFacadeImpl#update(com.axity.arquetipo.commons.dto.OfficeDto)}.
   */
  @Test
  void testUpdate()
  {
    var office = this.createOffice( 1 );

    var response = new GenericResponseDto<>( true );
    when( this.officeService.update( any( OfficeDto.class ) ) ).thenReturn( response );
    var result = this.officeFacade.update( office );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.axity.arquetipo.facade.impl.OfficeFacadeImpl#delete(java.lang.String)}.
   */
  @Test
  void testDelete()
  {
    var response = new GenericResponseDto<>( true );
    when( this.officeService.delete( any( Integer.class ) ) ).thenReturn( response );

    var result = this.officeFacade.delete( 9 );
    assertNotNull( result );
  }

  private OfficeDto createOffice( int i )
  {
    var office = new OfficeDto();
    office.setId( i );
    office.setCity( "City " + i );
    return office;
  }

  @Test
  void testFindGraphQL()
  {
    var list = new ArrayList<OfficeGraphQLDto>();
    when( this.officeService.findGraphQL( any( OfficeQueryDto.class ), any( DataFetchingEnvironment.class ) ) )
        .thenReturn( list );

    var query = new OfficeQueryDto();
    var result = this.officeFacade.findGraphQL( query, null );
    assertNotNull( result );
  }

  @Test
  void testProcessMessage()
  {
    assertDoesNotThrow( () -> this.officeFacade.processMessage( "Lorem impsum dolor sit amet" ) );
  }
}
