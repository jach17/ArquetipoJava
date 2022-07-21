package mx.com.axity.arquetipo.facade;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import mx.com.axity.arquetipo.commons.dto.OfficeDto;
import mx.com.axity.arquetipo.commons.request.PaginatedRequestDto;
import mx.com.axity.arquetipo.commons.response.GenericResponseDto;
import mx.com.axity.arquetipo.commons.response.PaginatedResponseDto;
import mx.com.axity.arquetipo.facade.impl.OfficeFacadeImpl;
import mx.com.axity.arquetipo.service.OfficeService;

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
   * {@link mx.com.axity.arquetipo.facade.impl.OfficeFacadeImpl#findOffices(mx.com.axity.arquetipo.commons.request.PaginatedRequestDto)}.
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
   * Test method for {@link mx.com.axity.arquetipo.facade.impl.OfficeFacadeImpl#find(java.lang.String)}.
   */
  @Test
  void testFind()
  {
    var response = new GenericResponseDto<OfficeDto> (this.createOffice( 1 ));
    when(this.officeService.find( any(String.class) )).thenReturn( response );
    
    var result = this.officeFacade.find( "1" );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link mx.com.axity.arquetipo.facade.impl.OfficeFacadeImpl#create(mx.com.axity.arquetipo.commons.dto.OfficeDto)}.
   */
  @Test
  void testCreate()
  {
    var office = this.createOffice( 8 );
    var response = new GenericResponseDto<>(office);
    when(this.officeService.create( any(OfficeDto.class) )).thenReturn( response );
    
    var result = this.officeFacade.create( office );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link mx.com.axity.arquetipo.facade.impl.OfficeFacadeImpl#update(mx.com.axity.arquetipo.commons.dto.OfficeDto)}.
   */
  @Test
  void testUpdate()
  {
    var office = this.createOffice( 1 );
    
    var response = new GenericResponseDto<>(true);
    when(this.officeService.update( any(OfficeDto.class) )).thenReturn( response );
    var result = this.officeFacade.update( office );
    assertNotNull( result );
  }

  /**
   * Test method for {@link mx.com.axity.arquetipo.facade.impl.OfficeFacadeImpl#delete(java.lang.String)}.
   */
  @Test
  void testDelete()
  {
    var response = new GenericResponseDto<>(true);
    when(this.officeService.delete( any(String.class) )).thenReturn( response );
    
    var result = this.officeFacade.delete( "9" );
    assertNotNull( result );
  }

  private OfficeDto createOffice( int i )
  {
    var office = new OfficeDto();
    office.setOfficeCode( String.valueOf( i ) );
    return office;
  }

}
