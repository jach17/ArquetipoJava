package com.[%= companylower %].[%= namelower %].facade;

import java.util.List;

import com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto;
import com.[%= companylower %].[%= namelower %].commons.request.PaginatedRequestDto;
import com.[%= companylower %].[%= namelower %].commons.request.graphql.[%= namecamel %]QueryDto;
import com.[%= companylower %].[%= namelower %].commons.response.GenericResponseDto;
import com.[%= companylower %].[%= namelower %].commons.response.PaginatedResponseDto;
import com.[%= companylower %].[%= namelower %].commons.response.graphql.[%= namecamel %]GraphQLDto;

import graphql.schema.DataFetchingEnvironment;

/**
 * Interface [%= namecamel %]Facade
 * 
 * @author [%= username %]
 */
public interface [%= namecamel %]Facade
{
  /**
   * Method to get [%= namecamel %]s
   * 
   * @param request
   * @return
   */
  PaginatedResponseDto<[%= namecamel %]Dto> find[%= namecamel %]s( PaginatedRequestDto request );

  /**
   * Method to get [%= namecamel %] by id
   * 
   * @param [%= namelower %]Id
   * @return
   */
  GenericResponseDto<[%= namecamel %]Dto> find( Integer [%= namelower %]Id );

  /**
   * Method to create a [%= namecamel %]
   * 
   * @param [%= namelower %]
   * @return
   */
  GenericResponseDto<[%= namecamel %]Dto> create( [%= namecamel %]Dto [%= namelower %] );

  /**
   * Method to update a [%= namecamel %]
   * 
   * @param [%= namelower %]
   * @return
   */
  GenericResponseDto<Boolean> update( [%= namecamel %]Dto [%= namelower %] );

  /**
   * Method to delete a [%= namecamel %]
   * 
   * @param [%= namelower %]Code
   * @return
   */
  GenericResponseDto<Boolean> delete( Integer [%= namelower %]Id );

  /**
   * Method to query by example
   * 
   * @param query
   * @param env
   * @return
   */
  List<[%= namecamel %]GraphQLDto> findGraphQL( [%= namecamel %]QueryDto query, DataFetchingEnvironment env );

  /**
   * Method to process a message
   * 
   * @param message
   */
  void processMessage( String message );
}
