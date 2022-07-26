package mx.com.axity.arquetipo.commons.request.graphql;

import lombok.Getter;
import lombok.Setter;
import mx.com.axity.arquetipo.commons.response.graphql.EmployeeGraphQLDto;

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
