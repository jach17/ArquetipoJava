package com.axity.arquetipo.commons.request.graphql;

import com.axity.arquetipo.commons.response.graphql.EmployeeGraphQLDto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class EmployeeQueryDto
{
  private EmployeeGraphQLDto query;
  private int limit;
  private int offset;
  private boolean reportsToProjection;
  private boolean officeProjection;
  private boolean customersProjection;

  /**
   * Constructor default
   */
  public EmployeeQueryDto()
  {
  }

  /**
   * Constructor por query
   * 
   * @param query
   */
  public EmployeeQueryDto( EmployeeGraphQLDto query )
  {
    this.query = query;
  }
}
