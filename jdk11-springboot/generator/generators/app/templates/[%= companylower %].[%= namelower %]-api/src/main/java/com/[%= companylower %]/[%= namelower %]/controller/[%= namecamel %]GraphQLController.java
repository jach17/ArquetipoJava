package com.[%= companylower %].[%= namelower %].controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.[%= companylower %].[%= namelower %].commons.request.graphql.[%= namecamel %]QueryDto;
import com.[%= companylower %].[%= namelower %].commons.response.graphql.[%= namecamel %]GraphQLDto;
import com.[%= companylower %].[%= namelower %].facade.[%= namecamel %]Facade;

import graphql.schema.DataFetchingEnvironment;

/**
 * [%= namecamel %] GraphQL controller class
 * 
 * @author [%= username %]
 *
 */
@Controller
public class [%= namecamel %]GraphQLController
{
  @Autowired
  private [%= namecamel %]Facade [%= namelower %]Facade;

  @QueryMapping(name = "[%= namelower %]s")
  public List<[%= namecamel %]GraphQLDto> getAllByExample( @Argument [%= namecamel %]GraphQLDto query, @Argument Integer page,
      @Argument Integer size, DataFetchingEnvironment env )
  {
    var wrapper = new [%= namecamel %]QueryDto();
    wrapper.setQuery( query );

    if( page != null )
    {
      wrapper.setPage( page );
    }

    if( size != null )
    {
      wrapper.setSize( size );
    }

    return this.[%= namelower %]Facade.findGraphQL( wrapper, env );
  }
}
