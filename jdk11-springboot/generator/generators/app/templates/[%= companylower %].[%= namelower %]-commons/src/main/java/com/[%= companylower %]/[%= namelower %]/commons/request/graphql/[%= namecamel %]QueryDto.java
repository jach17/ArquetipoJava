package com.[%= companylower %].[%= namelower %].commons.request.graphql;

import com.[%= companylower %].[%= namelower %].commons.response.graphql.[%= namecamel %]GraphQLDto;

import lombok.Getter;
import lombok.Setter;

/**
 * [%= namecamel %] Graphql Query Transfer object
 * 
 * @author [%= username %]
 */
@Getter
@Setter
public class [%= namecamel %]QueryDto
{
  private [%= namecamel %]GraphQLDto query;
  private int page = 0;
  private int size = 50;
}
