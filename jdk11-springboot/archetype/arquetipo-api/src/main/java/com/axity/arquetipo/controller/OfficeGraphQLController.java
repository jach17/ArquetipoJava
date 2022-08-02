package com.axity.arquetipo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.axity.arquetipo.commons.request.graphql.OfficeQueryDto;
import com.axity.arquetipo.commons.response.graphql.OfficeGraphQLDto;
import com.axity.arquetipo.facade.OfficeFacade;

import graphql.schema.DataFetchingEnvironment;

@Controller
public class OfficeGraphQLController
{
  @Autowired
  private OfficeFacade officeFacade;
  
  @QueryMapping(name = "offices")
  public List<OfficeGraphQLDto> getAllEmployeesByExample( @Argument OfficeQueryDto query,
      DataFetchingEnvironment env )
  {
    return this.officeFacade.findGraphQL( query, env );
  }
}
