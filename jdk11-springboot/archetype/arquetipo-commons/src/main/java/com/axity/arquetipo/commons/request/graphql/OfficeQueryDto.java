package com.axity.arquetipo.commons.request.graphql;

import com.axity.arquetipo.commons.response.graphql.OfficeGraphQLDto;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase OfficeQueryDto
 * 
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class OfficeQueryDto
{
  private OfficeGraphQLDto query;
  private int page = 0;
  private int size = 50;
}
