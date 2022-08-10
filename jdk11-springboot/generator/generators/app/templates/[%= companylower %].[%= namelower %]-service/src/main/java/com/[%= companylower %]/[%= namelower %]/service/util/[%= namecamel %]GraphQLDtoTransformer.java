package com.[%= companylower %].[%= namelower %].service.util;

import com.[%= companylower %].[%= namelower %].commons.response.graphql.[%= namecamel %]GraphQLDto;
import com.[%= companylower %].[%= namelower %].model.[%= namecamel %]DO;

/**
 * @author guillermo.segura@axity.com
 */
public final class [%= namecamel %]GraphQLDtoTransformer
{
  private [%= namecamel %]GraphQLDtoTransformer()
  {
  }

  /**
   * Utility class for transform a {@link com.[%= companylower %].[%= namelower %].model.[%= namecamel %]DO} into a
   * {@link com.[%= companylower %].[%= namelower %].commons.response.graphql.[%= namecamel %]GraphQLDto} class
   * 
   * @param entity
   * @return
   */
  public static [%= namecamel %]GraphQLDto transform( [%= namecamel %]DO entity )
  {
    [%= namecamel %]GraphQLDto [%= namelower %] = null;

    if( entity != null )
    {
      [%= namelower %] = new [%= namecamel %]GraphQLDto();
      [%= namelower %].set[%= namecamel %]Code( entity.get[%= namecamel %]Code() );
      [%= namelower %].setCity( entity.getCity() );
      [%= namelower %].setPhone( entity.getPhone() );
      [%= namelower %].setAddressLine1( entity.getAddressLine1() );
      [%= namelower %].setAddressLine2( entity.getAddressLine2() );
      [%= namelower %].setState( entity.getState() );
      [%= namelower %].setCountry( entity.getCountry() );
      [%= namelower %].setPostalCode( entity.getPostalCode() );
      [%= namelower %].setTerritory( entity.getTerritory() );
    }
    return [%= namelower %];
  }
}
