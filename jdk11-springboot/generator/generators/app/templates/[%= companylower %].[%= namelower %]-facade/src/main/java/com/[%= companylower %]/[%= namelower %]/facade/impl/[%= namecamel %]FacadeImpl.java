package com.[%= companylower %].[%= namelower %].facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto;
import com.[%= companylower %].[%= namelower %].commons.request.PaginatedRequestDto;
import com.[%= companylower %].[%= namelower %].commons.request.graphql.[%= namecamel %]QueryDto;
import com.[%= companylower %].[%= namelower %].commons.response.GenericResponseDto;
import com.[%= companylower %].[%= namelower %].commons.response.PaginatedResponseDto;
import com.[%= companylower %].[%= namelower %].commons.response.graphql.[%= namecamel %]GraphQLDto;
import com.[%= companylower %].[%= namelower %].facade.[%= namecamel %]Facade;
import com.[%= companylower %].[%= namelower %].service.[%= namecamel %]Service;

import graphql.schema.DataFetchingEnvironment;

/**
 * Class [%= namecamel %]FacadeImpl
 * @author [%= username %]
 */
@Service
@Transactional
public class [%= namecamel %]FacadeImpl implements [%= namecamel %]Facade
{
  @Autowired
  private [%= namecamel %]Service [%= namelower %]Service;
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<[%= namecamel %]Dto> find[%= namecamel %]s( PaginatedRequestDto request )
  {
    return this.[%= namelower %]Service.find[%= namecamel %]s( request );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<[%= namecamel %]Dto> find( Integer [%= namelower %]Id )
  {
    return this.[%= namelower %]Service.find( [%= namelower %]Id );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<[%= namecamel %]Dto> create( [%= namecamel %]Dto [%= namelower %] )
  {
    return this.[%= namelower %]Service.create( [%= namelower %] );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( [%= namecamel %]Dto [%= namelower %] )
  {
    return this.[%= namelower %]Service.update( [%= namelower %] );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( String [%= namelower %]Code )
  {
    return this.[%= namelower %]Service.delete( [%= namelower %]Code );
  }

  /**
   * {@inheritDoc}
   */
  public List<[%= namecamel %]GraphQLDto> findGraphQL( [%= namecamel %]QueryDto query, DataFetchingEnvironment env )
  {
    return this.[%= namelower %]Service.findGraphQL( query, env );
  }

  /**
   * {@inheritDoc}
   */
  public void processMessage( String message )
  {
    this.[%= namelower %]Service.processMessage( message );
  }
}
