package mx.com.axity.arquetipo.commons.response.graphql;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class OfficeResponseDto
{
  private String officeCode;
  private String city;
  private String phone;
  private String addressLine1;
  private String addressLine2;
  private String state;
  private String country;
  private String postalCode;
  private String territory;
}
