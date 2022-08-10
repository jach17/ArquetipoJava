package com.[%= companylower %].[%= namelower %].commons.response.graphql;

import lombok.Getter;
import lombok.Setter;

/**
 * [%= namecamel %]  GraphQL Transfer Object class
 * 
 * @author [%= username %]
 */
@Getter
@Setter
public class [%= namecamel %]GraphQLDto
{
  private Integer id;
  private String city;
  private String phone;
  private String addressLine1;
  private String addressLine2;
  private String state;
  private String country;
  private String postalCode;
  private String territory;
}
