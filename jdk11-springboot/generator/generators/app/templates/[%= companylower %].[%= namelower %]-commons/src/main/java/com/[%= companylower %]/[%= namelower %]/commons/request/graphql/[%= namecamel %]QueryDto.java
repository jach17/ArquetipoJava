package com.[%= companylower %].[%= namelower %].commons.request.graphql;

import com.[%= companylower %].[%= namelower %].commons.response.graphql.OfficeGraphQLDto;

import lombok.Getter;
import lombok.Setter;

/**
 * [%= namecamel %] Graphql Query Transfer object
 * 
 * @author [%= username %]
 */
@Getter
@Setter
public class OfficeQueryDto
{
  private OfficeGraphQLDto query;
  private int page = 0;
  private int size = 50;
}
