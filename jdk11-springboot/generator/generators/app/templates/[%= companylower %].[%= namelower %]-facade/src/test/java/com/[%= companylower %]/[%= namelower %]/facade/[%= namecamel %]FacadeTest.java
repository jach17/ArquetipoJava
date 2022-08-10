package com.[%= companylower %].[%= namelower %].facade;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto;
import com.[%= companylower %].[%= namelower %].commons.request.PaginatedRequestDto;
import com.[%= companylower %].[%= namelower %].commons.request.graphql.[%= namecamel %]QueryDto;
import com.[%= companylower %].[%= namelower %].commons.response.GenericResponseDto;
import com.[%= companylower %].[%= namelower %].commons.response.PaginatedResponseDto;
import com.[%= companylower %].[%= namelower %].commons.response.graphql.[%= namecamel %]GraphQLDto;
import com.[%= companylower %].[%= namelower %].facade.impl.[%= namecamel %]FacadeImpl;
import com.[%= companylower %].[%= namelower %].service.[%= namecamel %]Service;

import graphql.schema.DataFetchingEnvironment;

/**
 * Class [%= namecamel %]FacadeTest
 * 
 * @author [%= username %]
 */
class [%= namecamel %]FacadeTest
{
  private [%= namecamel %]Facade [%= namelower %]Facade;
  private [%= namecamel %]Service [%= namelower %]Service;

  @BeforeEach
  public void setUp()
  {
    this.[%= namelower %]Facade = new [%= namecamel %]FacadeImpl();

    this.[%= namelower %]Service = mock( [%= namecamel %]Service.class );
    ReflectionTestUtils.setField( this.[%= namelower %]Facade, "[%= namelower %]Service", this.[%= namelower %]Service );
  }

  /**
   * Test method for
   * {@link com.[%= companylower %].[%= namelower %].facade.impl.[%= namecamel %]FacadeImpl#find[%= namecamel %]s(com.[%= companylower %].[%= namelower %].commons.request.PaginatedRequestDto)}.
   */
  @Test
  void testFind[%= namecamel %]s()
  {
    int pageSize = 20;

    var data = new ArrayList<[%= namecamel %]Dto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.create[%= namecamel %]( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<[%= namecamel %]Dto>( 0, pageSize, 50, data );
    when( this.[%= namelower %]Service.find[%= namecamel %]s( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    var result = this.[%= namelower %]Facade.find[%= namecamel %]s( new PaginatedRequestDto( pageSize, 0 ) );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.[%= companylower %].[%= namelower %].facade.impl.[%= namecamel %]FacadeImpl#find(java.lang.String)}.
   */
  @Test
  void testFind()
  {
    var response = new GenericResponseDto<[%= namecamel %]Dto>( this.create[%= namecamel %]( 1 ) );
    when( this.[%= namelower %]Service.find( any( String.class ) ) ).thenReturn( response );

    var result = this.[%= namelower %]Facade.find( "1" );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.[%= companylower %].[%= namelower %].facade.impl.[%= namecamel %]FacadeImpl#create(com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto)}.
   */
  @Test
  void testCreate()
  {
    var [%= namelower %] = this.create[%= namecamel %]( 8 );
    var response = new GenericResponseDto<>( [%= namelower %] );
    when( this.[%= namelower %]Service.create( any( [%= namecamel %]Dto.class ) ) ).thenReturn( response );

    var result = this.[%= namelower %]Facade.create( [%= namelower %] );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.[%= companylower %].[%= namelower %].facade.impl.[%= namecamel %]FacadeImpl#update(com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto)}.
   */
  @Test
  void testUpdate()
  {
    var [%= namelower %] = this.create[%= namecamel %]( 1 );

    var response = new GenericResponseDto<>( true );
    when( this.[%= namelower %]Service.update( any( [%= namecamel %]Dto.class ) ) ).thenReturn( response );
    var result = this.[%= namelower %]Facade.update( [%= namelower %] );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.[%= companylower %].[%= namelower %].facade.impl.[%= namecamel %]FacadeImpl#delete(java.lang.String)}.
   */
  @Test
  void testDelete()
  {
    var response = new GenericResponseDto<>( true );
    when( this.[%= namelower %]Service.delete( any( String.class ) ) ).thenReturn( response );

    var result = this.[%= namelower %]Facade.delete( "9" );
    assertNotNull( result );
  }

  private [%= namecamel %]Dto create[%= namecamel %]( int i )
  {
    var [%= namelower %] = new [%= namecamel %]Dto();
    [%= namelower %].set[%= namecamel %]Code( String.valueOf( i ) );
    return [%= namelower %];
  }

  @Test
  void testFindGraphQL()
  {
    var list = new ArrayList<[%= namecamel %]GraphQLDto>();
    when( this.[%= namelower %]Service.findGraphQL( any( [%= namecamel %]QueryDto.class ), any( DataFetchingEnvironment.class ) ) )
        .thenReturn( list );

    var query = new [%= namecamel %]QueryDto();
    var result = this.[%= namelower %]Facade.findGraphQL( query, null );
    assertNotNull( result );
  }

}
